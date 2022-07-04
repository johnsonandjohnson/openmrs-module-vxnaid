CREATE VIEW `UpcomingDoseAgg` AS
select `UpcomingDoseHelpTable`.`Report date`                AS `Report date`,
       `UpcomingDoseHelpTable`.`Visit Status`               AS `Visit Status`,
       `UpcomingDoseHelpTable`.`Dose Number`                AS `Dose Number`,
       `UpcomingDoseHelpTable`.`Vaccine`                    AS `Vaccine`,
       `UpcomingDoseHelpTable`.`Clinic`                     AS `Clinic`,
       count(distinct `UpcomingDoseHelpTable`.`patient_id`) AS `CountOfUniquePatients`,
       cast(date_format(if((`UpcomingDoseHelpTable`.`Visit Status` = 'MISSED'), ((curdate() - weekday(curdate())) + 1),
                           if((`UpcomingDoseHelpTable`.`Start Date` < ((curdate() - weekday(curdate())) + 1)),
                              ((curdate() - weekday(curdate())) + 1),
                              ((`UpcomingDoseHelpTable`.`Start Date` - weekday(`UpcomingDoseHelpTable`.`Start Date`)) + 1))),
                        '%Y-%m-%d') as date)                AS `Scheduled Date`
from `UpcomingDoseHelpTable`
where ((`UpcomingDoseHelpTable`.`UpcomingDose` = 0) and (`UpcomingDoseHelpTable`.`LatestDate` = 'Yes') and
       ((`UpcomingDoseHelpTable`.`Visit Status` = 'MISSED') or (`UpcomingDoseHelpTable`.`Visit Status` = 'SCHEDULED')))
group by `UpcomingDoseHelpTable`.`Report date`, `UpcomingDoseHelpTable`.`Visit Status`,
         `UpcomingDoseHelpTable`.`Dose Number`, `UpcomingDoseHelpTable`.`Vaccine`, `UpcomingDoseHelpTable`.`Clinic`,
         `UpcomingDoseHelpTable`.`Scheduled Date`;
