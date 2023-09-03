package com.easydmarc.socialnetworkmvp.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record SortingCriteriaDto(@Nonnull String column, @Nonnull Direction direction, @Nonnull Integer languageId) {

    public enum Direction {
        ASC("asc"),
        DESC("desc");

        private final String name;

        Direction(String name) {
            this.name = name;
        }

        /**
         * Returns direction name
         *
         * @return direction name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns direction by direction name
         *
         * @param name
         *            direction name
         * @return direction by direction name
         */
        public static Direction getDirection(String name) {
            for (Direction dir : values()) {
                if (dir.getName().equals(name)) {
                    return dir;
                }
            }
            throw new IllegalArgumentException(String.format("Sort direction by name %s doesn't exist", name));
        }
    }
}
