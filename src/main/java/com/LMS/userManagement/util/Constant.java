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


    //NEGATIVE Message
    public static final String NO_COURSE = "No Courses Found";
    public static final String EMPTY_CART = "Cart is empty";
    public static final String NO_CARTNO_CART = "Cart not found";
    public static final String NO_DATA = "No data found";
    public static final String PROFILE_NOT_FOUND = "Profile not found";
    public static final String FAILED_COURSE = "Failed to retrieve courses";
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
    public static final String DEFAULT_PROFILE_IMAGE="iVBORw0KGgoAAAANSUhEUgAAA4QAAAOEBAMAAAALYOIIAAAAElBM" +
            "VEXR09Tm5uanqayTlZj///+Nj5LHzFG1AAAew0lEQVR42uzdXW6ryhIF4GrCANoRAyBY9z2ylXeQ7ghulPlP5YL" +
            "/Yu/4DwzVtapXnaOz1fvhKObLqm5owPLffXWrfbkYVsMfUc4qhP7fEL18wIuhOPtIQR7WatWR0OKw/yijKpLQ0nAs" +
            "31kgSWhgGOSlWlUkTDmMUWaosCJhmuFqFr9TSyWh8rCb0+9XkYRq6xdZpAIJlYayXIWKhIsPoyxcKxIuuwQVhVqRc" +
            "KlhpwK4nxRJuMBQD/D3LIOEcw51AQ9JxCA8/Fkd/t7oUFJU+C/EwYEgTCP4OyeS8MVhJwkrkPDlYRclbZHwxWF" +
            "qwF0QSTh9uBITRcLJQ7FSgYSThlEMVUXC8UOxVcMeBgnHDKOYq4qEY4YGBX8vfpPw8bATo0XCJ4dRzFYg4TNDMV0k" +
            "fDjsRKwbWiM0tgcWxXyV3PK9NwQQNHffsCnCTkCKhLfujhGYCiQ0cXeMkztr7BBCCe4MSXg5BBM83PZNwrOfA7A" +
            "qEuo86bK4IQmtbe6ONiQhsODekITIgiIlCTtsweHOqOwJwQV7w9wJ4QV/78fI9MkmB4LJ7zJNS+hC8GiYJaETw" +
            "YNhjoRuBPeGGRI6EtwZ5kfoSlASPtWdjNCZYMJ7vVMRuhM8GeZCuBLxapgJYSfi1jAPQqeC+5sTsyB0K5jmiYsEh" +
            "I4FkzxxkYBQXFcOhNE3of4bTtT3C70L6j80o03oX/B4iu+VsBXJxdApYSdZlGPCTASVv7VElVAkI0OXhDEfwuCTM" +
            "CPBwdAhYSdZVXBIKJlV5Y5QsitvhDE/wuCLMEPB3YNrfgizFNR6gFSHUDItP082xVwJVTbxNQjbbAX7M3wXhJ1kXD" +
            "4IJevyQJi34OlCGzBhzJxwf3YITSjZVwVOSMHFn8RfmDBS8NBKYQnpd2ylqITUO11oAyVkG/2dDjEJLQgWRfNbRc" +
            "JWikmY1C4Oeuttc1HDMFErhSRMGsKer7le601TS9C/3g34ZFNKwabZNPdq0yRopXhbvgkB7/vts9hHUX1VCkYYzX" +
            "XQP6WLGNAIY7IWOqb0WykQYaoIjivdORGLMJqdBBN20wBFmCSC62ZSqbZSGEKUCCobBhzCaH8WvJgR1ZppCUOIJ" +
            "ag5IVYghBFNsD+V1GqlGIQt0DSoPiGuIAiNn87bMDRN2GIKNhvVLQvLhB2ooJqh/SebYAW1DIP5LV9cQS3Dyjgh" +
            "sqDWmsY2ofIpYdEgGpamCZUFNw2kYWWYMIJnUOlaW7BL6EBQx7AyS4i9lDnWh0YMjRJGF4Iq02FllNBDG9UytE" +
            "nYehHUmA6DSUInbVRpOrRIGP0IarTSYI+wc9NGlVqpPUJXIdQwDOaebPIlqNFK53rV5VyErtqoUgwzJtQQbNYfG" +
            "jE0RKgIGBql0oihHcLorY3qtNLSEKG7NqoVQzOELkPYNJ/Lx9AMoc8QarRSK4Sa17ffNAkVWqkRQkXB903jK4Y" +
            "2CFunbVQzhokJfa5ldGOYltBzCNVimJbQcwjVYpiU0HcItWKY9Mkm3yFs1ovHMKTe8o3OQ6gQwzIxofMQasUwIaH" +
            "/EOrEMCGh5oWZVIQaMUxHmEMIVWKYjjCHEKrEMBlhdL+YUYphlQXhpvEcw1SEXSYh1NtzUifMYzEznBtq7TmpE+Y" +
            "SQr0YahNmE0KNdwqFJISSD6FWDJUJNXeZ3lITKu05KT/ZlFMINR500t/yjfksZnRuC65cEzZNBjEM2oRRMiOsNWLo" +
            "lrCwQNhEhRiqEmqGcN1k0Un7GGoS5hdChU5aqhJKfoQa5xWKhNktZnQIS0XCHEOo0EkDCeFjuFIjzLKP6r2YTYMw" +
            "zxAqdFLRImwzJdSL4eKEkmcfVXxb8NJPNqnOhO+WCBU6aaWy5Rtz7aMK10mnfCnXBMJs+6jOu/MVCGPGhAqdtFQg" +
            "lHz7qAahLE+YcwjVOikJgW/rnvDQ72jCnPuoTietFiaMeRNqdNJyYULJeirU+YbKZQlj7oQ6ndQPYZEnYViUUHIn" +
            "1OmkCxJm30eVvnJ7QUIhoUonXfDJJvZRFUKpFtvyjSRU6qSLEbKPKhHKUoSRhLqdFJ6wyJgwLEQoJNQilGUI2Uc" +
            "VJ8NyEUIhodae4aGTkhC6k1YLEEZOhZqE5QKEQkK1u0llzAsw7BKaFdRZzzz/AoynCSMJdQnL2QmFhLqTocATFtk" +
            "TPvsOk2f3C1sSanfSMPOWL/uoOqHMS9iREJ2wJeFZfSh10lkJ1QXfLRMqrWdkTkL9U4qChKf7L2YhjCRMMRnOSS" +
            "icClMQynyEkYRpCCsSgi9Jj510BkJ9wXfbhLVuJ4UkLEh41klfJ4wk/Kc+RbWTvk4oJEy1nhFYwtCQ8KyTvvxkU" +
            "5sghSQ87TjNseUbSZjmXtL9dsUMhF0CwaRfvmxqSToLYYo+an01o0gYZiCMJExJKDMQCgkTnhjOQdiSMOmSdOikLx" +
            "Km6KPmTwuxCFOEUEh40UlfI+xIiE7YkjDpjuGuk75GKCRMfVYhrxGm6aMFCecjTNNH30l40UlfIoxMYXpCeenJJi" +
            "Fh8sszIq9s+XYkvFWa/Sm8QBhJaODEEJGwYQqvddIphIn6aGiYwrkI05xSYKRQ9DvpFMJIQnRCIaGBG6BOnXQCYU" +
            "dCK4RhImFLQiOXZyYTCglN7DYdOykQYcEUzkTYMoV2CMMkwsgUohMmC+E7Ca920vFPNglTaIiwmrDlG0loibAkIT" +
            "phmEAoJDSzbT8UCdFTePslwTcJIwltpbAkIfRu024yHEsoJLyfQvXfcRKip1CqkYSRhMaWM1ICEb5BpFD9CIWRh" +
            "MIUGluRipAQfS487kg8SdiS0NxceLyp+0nClFMh58KRhFbep8658JnLpCO2fElocC688bZ1sfI+dc6FT+0ZkhB6Ljw" +
            "+sf0UoZDQYgqFhOjLmRGEkYQ2U1iSMB9CIaHNRhpQCN+ZwruT4ROEkSm0SliBEDKF9ydDAEI+2XR/MnyCMLWg+W+pGOpDkk2" +
            "GCIRM4d3J8PGTTS1TaHOj4rhn+HjLt2UKH9faNGEkod0UylOEQkK7KXyKsCMhegrTT4V8E+L9yfAhYfqpEOIKm2VCIaHR+y" +
            "6OnRSBEOBG0lrsEnZModntwkMnfUTYktB2Ch8TGljN8NtiXiMUC0XCe5MhCb0TdiQ0fVoof7+F69/9wkhC64Tlgy1fEtpP4Q" +
            "NCE4IAZxUkhL88k7Jb3SfsmELrC9I/X6T2D2FLQnRCG6sZ++sZEj4s6/ewfSQ9OncJhSm0viB9QNiREJ2wtUJYkPDhZGic0" +
            "PqJYTRLGJlC+wvS+4RCQgRCQSAUEj5PeL75FEmIsJo5PK99dcuXhCCEJQLhGwnvrWduEtoRtL2e+SQhOmGd/PAgEAoJH61nr" +
            "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";


}