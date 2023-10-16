
-- Roles to store ACL, FK to main table
CREATE TABLE IF NOT EXISTS public.FF4J_ROLES (
    FEAT_UID VARCHAR(50) REFERENCES FF4J_FEATURES(FEAT_UID),
    ROLE_NAME VARCHAR(50),
    PRIMARY KEY(FEAT_UID, ROLE_NAME)
    );