CREATE VIEW `DoseCompliancyDetail` AS
select `dos`.`Report date`                                                                           AS `Report Date`,
       `dos`.`id`                                                                                    AS `id`,
       if((`dos`.`Dose Number` = 1), 'First Dose', if((`dos`.`Dose Number` = NULL), 'Error - No Dose Number',
                                                      if((`dos`.`Date Difference in Days` > `dos`.`Up Window`),
                                                         'Dose given outside window',
                                                         if(((`dos`.`Date Difference in Days` * -(1)) > `dos`.`Low Window`),
                                                            'Dose given outside window', if(
                                                                    ((`dos`.`Date Difference in Days` <= `dos`.`Up Window`) and
                                                                     ((`dos`.`Date Difference in Days` * -(1)) <= `dos`.`Low Window`)),
                                                                    'Dose given within window',
                                                                    'Error - Unexpected Result'))))) AS `Compliance`,
       concat('Dose ', `dos`.`Dose Number`)                                                          AS `Dose Number`,
       `dos`.`patient_id`                                                                            AS `patientsID`,
       if(isnull(`dos`.`Vaccine`), 'NA', `dos`.`Vaccine`)                                            AS `Vaccine`,
       `dos`.`Clinic`                                                                                AS `Clinic`,
       cast(`dos`.`encounter_datetime` as date)                                                      AS `Encouterdate`,
       `dos`.`Planned Date`                                                                          AS `Planned Date`,
       `dos`.`Low Window`                                                                            AS `Low Window`,
       `dos`.`Up Window`                                                                             AS `Up Window`
from `DoseCompliancy` `dos`
where (`dos`.`Visit Status` = 'OCCURRED');
