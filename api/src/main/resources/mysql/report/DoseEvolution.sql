CREATE VIEW `DoseEvolution` AS
select curdate()                                                                                                        AS `Report date`,
       `v`.`visit_id`                                                                                                   AS `visit_id`,
       `vtype`.`name`                                                                                                   AS `name`,
       `v`.`patient_id`                                                                                                 AS `patient_id`,
       cast(`v`.`date_started` as date)                                                                                 AS `Start Date`,
       date_format(cast(`v`.`date_started` as date), '%Y-%m')                                                           AS `Year-Month`,
       `vsatt1`.`value_reference`                                                                                       AS `Visit Status`,
       concat('Dose ', (select `vsatt`.`value_reference`
                        from `visit_attribute` `vsatt`
                        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 5) and
                               (`vsatt`.`voided` = 0))))                                                                AS `Dose Number`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 6) and
               (`vsatt`.`voided` = 0)))                                                                                 AS `Vaccine`,
       (select `l`.`name`
        from `location` `l`
        where (`l`.`location_id` = `v`.`location_id`))                                                                  AS `Clinic`
from ((`visit` `v` left join `visit_type` `vtype` on ((`vtype`.`visit_type_id` = `v`.`visit_type_id`)))
         left join `visit_attribute` `vsatt1` on ((`vsatt1`.`visit_id` = `v`.`visit_id`)))
where ((`v`.`voided` = 0) and (`vtype`.`name` = 'Dosing') and (`vsatt1`.`value_reference` = 'OCCURRED') and
       (`vsatt1`.`attribute_type_id` = 2) and (`vsatt1`.`voided` = 0));
