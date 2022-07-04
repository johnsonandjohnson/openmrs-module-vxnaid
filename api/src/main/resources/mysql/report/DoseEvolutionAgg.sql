CREATE VIEW `DoseEvolutionAgg` AS
select `dos`.`Report date`                                AS `Report date`,
       `dos`.`Start Date`                                 AS `Start Date`,
       `dos`.`Year-Month`                                 AS `Year-Month`,
       `dos`.`Dose Number`                                AS `Dose Number`,
       if(isnull(`dos`.`Vaccine`), 'NA', `dos`.`Vaccine`) AS `Vaccine`,
       count(distinct `dos`.`patient_id`)                 AS `Count Of Distinct Patients`
from `DoseEvolution` `dos`
group by `dos`.`Report date`, `dos`.`Start Date`, `dos`.`Dose Number`, `dos`.`Vaccine`, `dos`.`Clinic`;
