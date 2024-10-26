package com.thesis.serverfurnitureecommerce.constant;

/**
 * A class that defines constants for user roles in the application.
 * <p>
 * This class is used to manage the different user roles that the system supports.
 * By defining these roles as constants, it helps to maintain consistency and avoid
 * hardcoding role names throughout the application.
 * </p>
 */
public class RoleConstant {
    /** The role for administrative users with full access. */
    public static final String ADMIN = "ADMIN";
    /** The role for standard users with limited access. */
    public static final String USER = "USER";
    /** The role for suppliers or moderators with specific privileges. */
    public static final String SUPPLIER = "MOD";
}