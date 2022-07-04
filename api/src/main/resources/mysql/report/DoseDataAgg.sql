CREATE VIEW `DoseDataAgg` AS
select `dd`.`Report date`                                                                   AS `Report date`,
       (cast(`dd`.`Start Date` as date) - interval (dayofmonth(`dd`.`Start Date`) - 1) day) AS `first_day_of_month`,
       `dd`.`Dose Number`                                                                   AS `Dose Number`,
       if(isnull(`dd`.`Vaccine`), 'NA', `dd`.`Vaccine`)                                     AS `Vaccine`,
       `dd`.`Clinic`                                                                        AS `Clinic`,
       count(`dd`.`patient_id`)                                                             AS `Count of Patients`
from `DoseData` `dd`
where ((`dd`.`Visit Status` = 'OCCURRED') and (`dd`.`name` = 'Dosing') and (`dd`.`Dose Number` <> 'Dose 99'))
group by `dd`.`Report date`, `first_day_of_month`, `dd`.`Dose Number`, `dd`.`Vaccine`, `dd`.`Clinic`;
