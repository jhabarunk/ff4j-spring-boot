CREATE TABLE IF NOT EXISTS public.FF4J_CUSTOM_PROPERTIES (
    PROPERTY_ID   VARCHAR(100) NOT NULL,
    CLAZZ          VARCHAR(255) NOT NULL,
    CURRENTVALUE  VARCHAR(255),
    FIXEDVALUES   VARCHAR(1000),
    DESCRIPTION   VARCHAR(1000),
    FEAT_UID      VARCHAR(100) REFERENCES public.ff4j_features (FEAT_UID),
    PRIMARY KEY(PROPERTY_ID, FEAT_UID)
    );