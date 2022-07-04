CREATE VIEW `DoseCompliancyAggregate` AS
select `dos`.`Report date`                                                                           AS `Report date`,
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
       count(distinct `dos`.`patient_id`)                                                            AS `number of distinct patients`,
       if(isnull(`dos`.`Vaccine`), 'NA', `dos`.`Vaccine`)                                            AS `Vaccine`,
       `dos`.`Clinic`                                                                                AS `Clinic`,
       (cast(`dos`.`encounter_datetime` as date) -
        interval (dayofmonth(`dos`.`encounter_datetime`) - 1) day)                                   AS `first_day_of_month`
from `DoseCompliancy` `dos`
where (`dos`.`Visit Status` = 'OCCURRED')
group by `dos`.`Dose Number`, `dos`.`Clinic`, `Compliance`, `first_day_of_month`;
