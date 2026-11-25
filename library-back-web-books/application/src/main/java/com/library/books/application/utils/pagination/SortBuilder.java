package com.library.books.application.utils.pagination;

import java.util.Arrays;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@UtilityClass
public class SortBuilder {
  private static final String FIELD_SEP = ";";
  private static final String DIRECTION_SEP = ",";
  private static final String ASC_PREFIX = "+";
  private static final String DESC_PREFIX = "-";

  public static Sort of(String sort) {
    if (!StringUtils.hasText(sort)) return Sort.unsorted();

    var entries = sort.split(FIELD_SEP);
    var hasPrefixFormat = Arrays.stream(entries)
      .map(String::trim)
      .anyMatch(entry -> entry.startsWith(ASC_PREFIX) || entry.startsWith(DESC_PREFIX));
    var hasCommaFormat = Arrays.stream(entries).anyMatch(entry -> entry.contains(DIRECTION_SEP));
    if (hasPrefixFormat && hasCommaFormat) {
      throw new IllegalArgumentException("Use only one sort direction format per request");
    }

    return Arrays.stream(entries)
      .map(entry -> hasCommaFormat
        ? parseCommaSortField(entry)
        : parsePrefixSortField(entry))
      .reduce(Sort::and)
      .orElse(Sort.unsorted());
  }

  private static Sort parsePrefixSortField(String entry) {
    var field = entry.trim();
    var prefixed = field.startsWith(ASC_PREFIX) || field.startsWith(DESC_PREFIX);
    var direction = field.startsWith(DESC_PREFIX) ? Sort.Direction.DESC : Sort.Direction.ASC;
    if (prefixed) field = field.substring(1);
    if (field.isBlank()) {
      throw new IllegalArgumentException("Sort field cannot be empty");
    }

    return Sort.by(direction, field);
  }

  private static Sort parseCommaSortField(String entry) {
    var parts = entry.split(DIRECTION_SEP, 2);
    var field = parts[0].trim();
    if (field.isEmpty()) {
      throw new IllegalArgumentException("Sort field cannot be empty");
    }

    var direction = parts.length > 1
      ? Sort.Direction.fromString(parts[1].trim())
      : Sort.Direction.ASC;
    return Sort.by(direction, field);
  }
}
