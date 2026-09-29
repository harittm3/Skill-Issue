# SKILL ISSUE — Issue #404

> *Pick your engineering role, confess your skill ratings, and receive a brutally honest AI-generated roast plus a "chance of getting hired" percentage. Comic-book edition.*

---

## Features

- **6 target roles** — SDE, Data Analyst, Full Stack Engineer, AI Engineer, ML Engineer, Cloud Engineer
- **Weighted scoring** — each role has 5 subcategories with custom weights; your self-ratings produce a computed hiring-probability percentage
- **AI roast generation** with a three-tier fallback chain:
  1. Groq API (`openai/gpt-oss-120b`) — primary
  2. Gemini API (`gemini-3.5-flash-lite`) — fallback if Groq fails
  3. Hardcoded canned roasts (10 per role) — last resort if both APIs are down
- **Per-IP rate limiting** via Resilience4j — 1 request per 10 seconds per IP on `POST /api/roast`
- **Comic-book themed frontend** — static HTML/CSS/JS with Bangers + Comic Neue fonts, halftone panels, and SVG comic art
- **Input validation** — bean validation on the request body; all error paths return JSON

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 4.1.1, Java 21, Maven 3.9.16 |
| REST | Spring Web MVC (`spring-boot-starter-webmvc`) |
| Validation | `spring-boot-starter-validation` (Jakarta Bean Validation) |
| Rate limiting | Resilience4j 2.2.0 (`resilience4j-spring-boot3`) |
| HTTP client | Spring `RestClient` (connect timeout 3 s, read timeout 8 s) |
| AI providers | Groq API, Google Gemini API (plain HTTP, no SDK) |
| Frontend | Static HTML + CSS + vanilla JS (`src/main/resources/static`) |

---

## Prerequisites

- **Java 21** (JDK 21+)
- **Maven** — not required if you use the included Maven Wrapper (`./mvnw` / `mvnw.cmd`); the wrapper downloads Maven 3.9.16 automatically
- API keys for **Groq** and **Gemini** (see Setup below)

---

## Setup

### 1 — Clone the repo

```bash
git clone https://github.com/harittm3/Skill-Issue.git
cd Skill-Issue
```

### 2 — Set the required environment variables

The app will fail to start without both keys. Set them in your shell before running:

**macOS / Linux (bash / zsh)**
```bash
export GROQ_API_KEY="gsk_your_groq_key_here"
export GEMINI_API_KEY="your_gemini_key_here"
```

**Windows — Command Prompt**
```cmd
set GROQ_API_KEY=gsk_your_groq_key_here
set GEMINI_API_KEY=your_gemini_key_here
```

**Windows — PowerShell**
```powershell
$env:GROQ_API_KEY = "gsk_your_groq_key_here"
$env:GEMINI_API_KEY = "your_gemini_key_here"
```

> Get a Groq key at [console.groq.com](https://console.groq.com) and a Gemini key at [aistudio.google.com](https://aistudio.google.com).

### 3 — Run

```bash
./mvnw spring-boot:run        # macOS / Linux
mvnw.cmd spring-boot:run      # Windows
```

The app starts on **http://localhost:8080**. Open that in your browser to reach the frontend.

---

## API Documentation

### `POST /api/roast`

Compute a hiring-probability score and generate a roast for the given role and skill ratings.

#### Rate limit
1 request per 10 seconds per IP. Exceeding it returns HTTP **429** with:
```json
{ "error": "Too many requests. Please try again later." }
```

> The client IP is resolved from the `X-Forwarded-For` header when present, falling back to the direct connection address otherwise. If you deploy this behind a reverse proxy, make sure the proxy sets that header to the real client IP itself (overwriting anything a client sends) — otherwise the limiter can be trivially bypassed by forging the header.

---

#### Request body

```json
{
  "role": "<role name>",
  "ratings": {
    "<subcategory name>": <integer 1–10>,
    ...
  }
}
```

| Field | Type | Required | Notes |
|---|---|---|---|
| `role` | `string` | ✅ | Must be one of the valid role names (see table below) |
| `ratings` | `object` | ✅ | Keys are exact subcategory names; values are integers 1–10 |

All subcategory keys for the chosen role must be present. Ratings outside 1–10 or missing keys return HTTP **400**.

---

#### Valid roles and their subcategories

| Role (exact string) | Subcategories (exact key names) | Weights |
|---|---|---|
| `SDE` | `Data Structures`, `Programming Languages`, `Core Computer Science`, `System Design`, `Debugging` | 25%, 25%, 20%, 20%, 10% |
| `Data Analyst` | `SQL`, `Excel/ Sheets`, `Python`, `Statistics`, `Power BI/ Tableau` | 25%, 25%, 20%, 20%, 10% |
| `Full Stack Engineer` | `Backend`, `Frontend`, `Databases`, `APIs Design`, `Cloud Deployment` | 25%, 25%, 20%, 20%, 10% |
| `AI Engineer` | `Python AI`, `LLM`, `Vector DB`, `RAG Framework`, `Prompt Engineering` | 25%, 25%, 20%, 20%, 10% |
| `ML Engineer` | `Python ML`, `Scikit Learn`, `PyTorch/ Tensorflow`, `Pandas/ Numpy`, `MLOps` | 25%, 25%, 20%, 20%, 10% |
| `Cloud Engineer` | `Cloud Platforms`, `Networking`, `Containers`, `CI/CD Automation`, `Linux` | 25%, 25%, 20%, 20%, 10% |

---

#### Response body (HTTP 200)

```json
{
  "percentage": 56,
  "roast": "You call yourself an SDE but...",
  "role": "SDE",
  "breakdown": {
    "Data Structures": 7,
    "Programming Languages": 5,
    "Core Computer Science": 6,
    "System Design": 8,
    "Debugging": 4
  }
}
```

| Field | Type | Notes |
|---|---|---|
| `percentage` | `int` | Computed hiring-probability, 10–90 |
| `roast` | `string` | AI-generated (or canned) roast text |
| `role` | `string` | Echoes the role from the request |
| `breakdown` | `object` | Echoes the ratings map from the request |

---

#### Example `curl` commands

**SDE request:**
```bash
curl -s -X POST http://localhost:8080/api/roast \
  -H "Content-Type: application/json" \
  -d '{
    "role": "SDE",
    "ratings": {
      "Data Structures": 7,
      "Programming Languages": 5,
      "Core Computer Science": 6,
      "System Design": 8,
      "Debugging": 4
    }
  }'
```

**ML Engineer request:**
```bash
curl -s -X POST http://localhost:8080/api/roast \
  -H "Content-Type: application/json" \
  -d '{
    "role": "ML Engineer",
    "ratings": {
      "Python ML": 6,
      "Scikit Learn": 5,
      "PyTorch/ Tensorflow": 4,
      "Pandas/ Numpy": 7,
      "MLOps": 3
    }
  }'
```

**Error response example (invalid role):**
```json
{ "error": "Unknown role: \"wizard\". Valid roles are: SDE, Data Analyst, Full Stack Engineer, AI Engineer, ML Engineer, Cloud Engineer" }
```

---

## Project Structure

```
src/main/java/com/skillissue/roastapp/
│
├── RoastappApplication.java          # Spring Boot entry point
│
├── config/
│   ├── RoleConfig.java               # Defines all roles and subcategory weights as Spring beans
│   ├── RateLimiterConfiguration.java # Resilience4j rate-limiter bean (1 req / 10 s)
│   ├── AppConfig.java                # RestClient bean with timeout settings
│   ├── GroqProperties.java           # @ConfigurationProperties for groq.api.*
│   └── GeminiProperties.java         # @ConfigurationProperties for gemini.api.*
│
├── controller/
│   └── RoastController.java          # POST /api/roast — validates, scores, roasts
│
├── service/
│   ├── ScoringService.java           # Weighted percentage calculation
│   └── RoastService.java             # Fallback chain: Groq → Gemini → Canned
│
├── provider/
│   ├── RoastProvider.java            # Interface implemented by all three providers
│   ├── GroqProvider.java             # Calls Groq chat completions API
│   ├── GeminiProvider.java           # Calls Google Gemini generateContent API
│   ├── CannedProvider.java           # 10 hardcoded roasts per role, picked at random
│   └── Groq*/Gemini*.(java)          # Request/response DTOs for each provider
│
├── model/
│   ├── RoastRequest.java             # Record: role + ratings map
│   ├── RoastResponse.java            # Record: percentage, roast, role, breakdown
│   ├── Role.java                     # Record: name + subcategory list
│   └── Subcategory.java              # Record: name + weight
│
├── filter/
│   └── RateLimitFilter.java          # Servlet filter — per-IP rate limiting on POST /api/*
│
└── exception/
    ├── InvalidRoleException.java     # Thrown for unknown role strings
    └── GlobalExceptionHandler.java   # @RestControllerAdvice → JSON error responses

src/main/resources/
├── application.properties            # App config and API endpoint/model settings
└── static/
    ├── index.html                    # Landing page — role picker
    ├── profile.html                  # Skill rating form
    ├── result.html                   # Roast result display
    ├── css/                          # Comic-book styles (base, components, form, home, result)
    ├── js/result.js                  # Fetches /api/roast and renders the result page
    └── images/                       # SVG comic art assets
```

---

## Notes

This is a **personal / portfolio project** built for fun. It is not production software — no auth, no persistent storage, no multi-instance deployment. The rate limiter is in-process (per JVM instance) and resets on restart. Run it locally and don't expose it to the public internet without at minimum securing your API keys via proper secrets management, and putting a reverse proxy in front that sets `X-Forwarded-For` correctly (see the rate limit note above).