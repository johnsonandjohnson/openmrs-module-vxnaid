CREATE VIEW `DoseData` AS
select curdate()                                                                                                        AS `Report date`,
       `v`.`visit_id`                                                                                                   AS `visit_id`,
       `vtype`.`name`                                                                                                   AS `name`,
       `v`.`patient_id`                                                                                                 AS `patient_id`,
       cast(`v`.`date_started` as date)                                                                                 AS `Start Date`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 1) and
               (`vsatt`.`voided` = 0)))                                                                                 AS `Visit Time`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 2) and
               (`vsatt`.`voided` = 0)))                                                                                 AS `Visit Status`,
       cast((select `vsatt`.`value_reference`
             from `visit_attribute` `vsatt`
             where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 3) and
                    (`vsatt`.`voided` = 0))) as decimal(10, 0))                                                         AS `Low Window`,
       cast((select `vsatt`.`value_reference`
             from `visit_attribute` `vsatt`
             where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 4) and
                    (`vsatt`.`voided` = 0))) as decimal(10, 0))                                                         AS `Up Window`,
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
from (`visit` `v`
         left join `visit_type` `vtype` on ((`vtype`.`visit_type_id` = `v`.`visit_type_id`)))
where (`v`.`voided` = 0);
