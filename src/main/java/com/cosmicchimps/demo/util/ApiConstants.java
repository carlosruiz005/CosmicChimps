package com.cosmicchimps.demo.util;

/**
 *
 * @author Carlos Ruiz at getweb.mx
 */
public class ApiConstants {

    public static final String CONTENT_TYPE_JSON_APPLICATION = "application/json";
    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_200_DESCRIPTION = "Solicitud resuelta.";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_400_DESCRIPTION = "Solicitud mal formada.";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_404_DESCRIPTION = "Recurso no encontrado.";

    public static class ExamenControllerConstants {

        public static final String URL = "/schedule";

        public static class AddSchedule {

            public static final String URL = "/";
            public static final String URL_SUMMARY = "Add Schedule";
            public static final String URL_DESCRIPTION = "As a Rest User I want to add a Schedule";
        }

        public static class ValidSchedule {

            public static final String URL = "/{id}/is-valid/{date}";
            public static final String URL_SUMMARY = "Validate Date";
            public static final String URL_DESCRIPTION = "As a Rest User I want to validate a date with a schedule";

        }
    }
}
