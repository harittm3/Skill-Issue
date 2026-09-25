package com.skillissue.roastapp.model;

import java.util.List;

public record Role(String name, List<Subcategory> subcategories) {}
