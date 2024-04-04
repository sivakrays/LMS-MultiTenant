package com.LMS.userManagement.util;

import org.springframework.http.HttpStatus;
import software.amazon.awssdk.http.HttpStatusCode;

public class Constant {

    // POSITIVE Message
    public static final String USER_EXISTS = "User Already Exists";
    public static final String DATA_FOUND = "Data found";
    public static final String TENANT_EXISTS = "Tenant already exists";
    public static final String COURSE_EXISTS_CART = "Course already exists in the cart";
    public static final String COURSES_FOUND = "Courses found";
    public static final String PROFILE_FOUND = "Profile found";
    public static final String CART_DETAILS_FOUND = "Cart details found";
    public static final String TENANTS_FOUND = "Tenants Found";
    public static final String USER_REGISTERED = "User Registered Successfully";
    public static final String CART_DELETED = "Cart Deleted Successfully";
    public static final String LOGIN_SUCCESS = "Login Successful";
    public static final String SUCCESS_FILE = "File retrieved successfully";
    public static final String UPDATED_VIDEO_DURATION = "Course Video Duration updated";
    public static final String SAVED_VIDEO_DURATION = "Course Video Duration saved";
    public static final String TENANT_REGISTERED_SUCCESSFUL = "Tenant registered successfully";
    public static final String ADMIN_REGISTERED = "Admin registered successfully";
    public static final String SCHEMA_UPDATED = "Schema updated successfully";
    public static final String COURSE_UPDATED = "Course updated successfully";
    public static final String PROFILE_UPDATED = "Profile updated successfully";
    public static final String COURSE_SAVED = "Course saved successfully";
    public static final String CART_SAVED = "Cart saved successfully";
    public static final String SECTION_SAVED = "Section saved successfully";
    public static final String SUBSECTION_SAVED = "Subsection saved successfully";
    public static final String SECTION_UPDATED = "Section updated successfully";
    public static final String EMPTY_SEARCH = "Search string is empty";
    public static final String QUIZ_UPDATED = "Quiz updated successfully";
    public static final String BADGE_SAVED = "Badge saved successfully";
    public static final String BADGE_UPDATED = "Badge updated successfully";
    public static final String QUIZ_CSV_UPLOAD = "Quiz CSV uploaded successfully";
    public static final String QUIZ_CSV_DOWNLOAD = "Quiz CSV template downloaded successfully";
    public static final String CART_EMPTY_DELETION = "Cart is empty after deletion";
    public static final String COURSE_PURCHASED = "Course purchased successfully";
    public static final String COURSE_IDS_EMPTY = "Course ids are empty";
    public static final String RETRIEVED_PURCHASED_COURSE = "Purchased courses found";
    public static final String SAVE_HTML_COURSE = "Html course save successfully";
    public static final String CHAPTER_NOT_FOUND = "Chapter not found";
    public static final String UPDATED_CHAPTER = "Chapter updated successfully";
    public static final String CHAPTER_CONTENT_NOT_FOUND = "Chapter content not found";
    public static final String UPDATED_CHAPTER_CONTENT = "Chapter content updated successfully";








    //NEGATIVE Message
    public static final String NO_COURSE = "No Courses Found";
    public static final String FAILED_PURCHASE_COURSE = "Failed to purchase course";
    public static final String FAILED_RETRIEVED_PURCHASED_COURSE = "Failed to retrieve purchased course";
    public static final String FAILED_SAVE_HTML_COURSE = "Failed to save html course";

    public static final String FAILED_UPDATE_CHAPTER = "Failed to update chapter details";
    public static final String FAILED_UPDATE_CHAPTER_CONTENT = "Failed to update chapter content details";



    public static final String EMPTY_CART = "Cart is empty";
    public static final String NO_CARTNO_CART = "Cart not found";
    public static final String NO_DATA = "No data found";
    public static final String PROFILE_NOT_FOUND = "Profile not found";
    public static final String FAILED_COURSE = "Failed to retrieve courses";
    public static final String FAILED_VIDEO_DURATION = "Failed to save course video duration";
    public static final String FAILED_RETRIEVED_PROFILE = "Failed to retrieve profile";
    public static final String FAILED_TENANT = "Failed to retrieve tenants";
    public static final String FAILED_USER_STATS = "Failed to retrieve user statistics";
    public static final String FAILED_DELETE_CART = "Failed to delete cart";
    public static final String FAILED_CART_DETAILS = "Failed to retrieve cart details";
    public static final String FAILED_FILES = "Failed to retrieve files";
    public static final String FAILED_RETRIEVE_USERS = "Failed to retrieve users";
    public static final String NO_FILES = "No files found";
    public static final String NO_TENANTS = "No Tenants Found";
    public static final String FAILED_REGISTER_TENANT = "Failed to register tenant";
    public static final String LOGIN_FAILED = "Login failed";
    public static final String USER_NOT_FOUND = "User not found or incorrect credentials";
    public static final String USERS_NOT_FOUND = "No data Found";
    public static final String ADMIN_NOT_FOUND = "Admin not found or incorrect credentials";
    public static final String FAILED_ADMIN_REGISTER = "Failed to register admin";
    public static final String REMOVED_USER = "User removed successfully";
    public static final String FAILED_DELETE_TENANT = "Failed to delete tenant";
    public static final String CART_FAILED = "Failed to save cart";
    public static final String TENANT_NOT_FOUND_BY_EMAIL = "Tenant not found for email";
    public static final String TENANT_NOT_FOUND = "Tenant not found";
    public static final String SCHEMA_UPDATE_FAILED = "Failed to update schema";
    public static final String SECTION_UPDATE_FAILED = "Failed to update section";
    public static final String SUBSECTION_UPDATE_FAILED = "Failed to update subsection";
    public static final String COURSE_UPDATE_FAILED = "Failed to update course";
    public static final String COURSE_SAVE_FAILED = "Failed to save course";
    public static final String SECTION_SAVE_FAILED = "Failed to save section";
    public static final String DELETE_COURSE = "Course Deleted Successfully";
    public static final String USER_DELETED = "User Deleted Successfully";
    public static final String FAILED_DELETE_USER = "Failed to delete user";
    public static final String DELETE_COURSE_FAILED = "Failed to Delete Course";
    public static final String SEARCH_FAILED = "Failed to search courses";
    public static final String FAILED_COURSES_FETCH = "Failed to fetch courses";
    public static final String FAILED_QUIZ_UPDATE = "Failed to update quiz";
    public static final String FAILED_BADGE = "Failed to update/save badge";
    public static final String FAILED_PROFILE_SAVE_EDIT = "Failed to edit/save profile";
    public static final String FAILED_QUIZ_CSV_UPLOAD = "Failed to upload Quiz CSV";
    public static final String FAILED_QUIZ_CSV_DOWNLOAD = "Quiz CSV template downloaded failed";

    //status Code
    public static final int NO_CONTENT = 204;
    public static final int FORBIDDEN = 403;
    public static final int SUCCESS = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int UNAUTHORIZED = 401;


    //Common Data
    public static final String CREATED_DATE="createdDate";
    public static final String DEFAULT_PROFILE_IMAGE="https://t4.ftcdn.net/jpg/05/89/93/27/360_F_589932782_vQAEAZhHnq1QCGu5ikwrYaQD0Mmurm0N.jpg";


}