CREATE VIEW `UpcomingDoseHelpTable` AS
select `up`.`Report date`                                                                                    AS `Report date`,
       `up`.`visit_id`                                                                                       AS `visit_id`,
       `up`.`name`                                                                                           AS `name`,
       `up`.`patient_id`                                                                                     AS `patient_id`,
       `up`.`Start Date`                                                                                     AS `Start Date`,
       `up`.`Visit Status`                                                                                   AS `Visit Status`,
       concat('Dose ', `up`.`Dose Number`)                                                                   AS `Dose Number`,
       `up`.`Vaccine`                                                                                        AS `Vaccine`,
       `up`.`Clinic`                                                                                         AS `Clinic`,
       (select count(`up1`.`visit_id`)
        from `UpcomingDose` `up1`
        where ((`up`.`patient_id` = `up1`.`patient_id`) and (`up`.`Dose Number` = `up1`.`Dose Number`) and
               (`up1`.`Visit Status` = 'OCCURRED') and
               (`up1`.`name` = 'Dosing')))                                                                   AS `UpcomingDose`,
       if(((select max(`up2`.`Start Date`)
            from `UpcomingDose` `up2`
            where ((`up`.`patient_id` = `up2`.`patient_id`) and (`up`.`Dose Number` = `up2`.`Dose Number`) and
                   (`up2`.`name` = 'Dosing'))) = `up`.`Start Date`), 'Yes', 'No')                            AS `LatestDate`,
       cast(date_format(if((`up`.`Visit Status` = 'MISSED'), ((curdate() - weekday(curdate())) + 1),
                           if((`up`.`Start Date` < ((curdate() - weekday(curdate())) + 1)),
                              ((curdate() - weekday(curdate())) + 1),
                              ((`up`.`Start Date` - weekday(`up`.`Start Date`)) + 1))),
                        '%Y-%m-%d') as date)                                                                 AS `Scheduled Date`
from `UpcomingDose` `up`
where ((`up`.`Dose Number` <> 99) and (`up`.`Dose Number` <> 1) and ((select count(`up1`.`visit_id`)
                                                                      from `UpcomingDose` `up1`
                                                                      where ((`up`.`patient_id` = `up1`.`patient_id`) and
                                                                             (`up`.`Dose Number` = `up1`.`Dose Number`) and
                                                                             (`up1`.`Visit Status` = 'OCCURRED') and
                                                                             (`up1`.`name` = 'Dosing'))) = 0));
