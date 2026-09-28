document.addEventListener('DOMContentLoaded', () => {
  const rawData = sessionStorage.getItem('roastResult');
  if (!rawData) {
    window.location.replace('index.html');
    return;
  }

  let data;
  try {
    data = JSON.parse(rawData);
  } catch (e) {
    window.location.replace('index.html');
    return;
  }

  if (!data || typeof data.percentage !== 'number') {
    window.location.replace('index.html');
    return;
  }

  function getTier(pct) {
    if (pct < 40) return 'low';
    if (pct < 70) return 'mid';
    return 'high';
  }

  // 1. Score Number
  const scoreNumber = document.getElementById('score-number');
  if (scoreNumber) {
    scoreNumber.textContent = `${data.percentage}%`;
  }

  // 2. Role Badge
  const roleBadge = document.getElementById('role-badge');
  if (roleBadge) {
    roleBadge.textContent = data.role || 'Role';
  }

  // 3. Health Bar
  const scoreHp = document.getElementById('score-hp');
  const scoreFill = document.getElementById('score-fill');
  const tier = getTier(data.percentage);

  if (scoreHp) {
    scoreHp.setAttribute('data-tier', tier);
    scoreHp.setAttribute('aria-valuenow', data.percentage.toString());
    scoreHp.setAttribute('aria-label', `${data.percentage} percent`);
  }

  if (scoreFill) {
    scoreFill.style.setProperty('--value', `${data.percentage}%`);
  }

  // 4. Roast Text (Using textContent, never innerHTML)
  const roastText = document.getElementById('roast-text');
  if (roastText) {
    roastText.textContent = data.roast || 'No roast available.';
  }

  // 5. Breakdown Cards
  const breakdownContainer = document.getElementById('breakdown-container');
  if (breakdownContainer) {
    // Clear the 3 placeholder cards
    breakdownContainer.replaceChildren();

    const breakdown = data.breakdown || {};
    const entries = Object.entries(breakdown);

    if (entries.length > 0) {
      entries.forEach(([name, rating]) => {
        const numRating = typeof rating === 'number' ? rating : 5;
        const subPct = Math.min(Math.max(numRating * 10, 0), 100);
        const lost = 10 - numRating;

        const card = document.createElement('div');
        card.className = 'panel panel--flat';

        const h3 = document.createElement('h3');
        h3.textContent = name;

        const p = document.createElement('p');
        p.className = 'muted';
        p.textContent = lost === 0
          ? 'Full score! (10 / 10)'
          : `${lost} point${lost > 1 ? 's' : ''} lost (${numRating} / 10)`;

        const hp = document.createElement('div');
        hp.className = 'hp';
        hp.setAttribute('data-tier', getTier(subPct));
        hp.setAttribute('role', 'progressbar');
        hp.setAttribute('aria-valuenow', subPct.toString());
        hp.setAttribute('aria-valuemin', '0');
        hp.setAttribute('aria-valuemax', '100');
        hp.setAttribute('aria-label', `${name}: ${subPct}%`);

        const fill = document.createElement('div');
        fill.className = 'hp__fill';
        fill.style.setProperty('--value', `${subPct}%`);

        hp.appendChild(fill);
        card.appendChild(h3);
        card.appendChild(p);
        card.appendChild(hp);
        breakdownContainer.appendChild(card);
      });
    } else {
      const emptyMsg = document.createElement('p');
      emptyMsg.className = 'muted';
      emptyMsg.textContent = 'No subcategory breakdown available.';
      breakdownContainer.appendChild(emptyMsg);
    }
  }
});
