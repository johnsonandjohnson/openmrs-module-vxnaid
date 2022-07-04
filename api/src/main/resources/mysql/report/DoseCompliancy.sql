CREATE VIEW `DoseCompliancy` AS
select curdate()                                                           AS `Report date`,
       `enc`.`encounter_id`                                                AS `id`,
       `enc`.`patient_id`                                                  AS `patient_id`,
       `l`.`name`                                                          AS `Clinic`,
       `enc`.`encounter_datetime`                                          AS `encounter_datetime`,
       `enc`.`visit_id`                                                    AS `visit_id`,
       `vtype`.`name`                                                      AS `name`,
       cast(`v`.`date_started` as date)                                    AS `Planned Date`,
       cast((to_days(cast(`enc`.`encounter_datetime` as date)) -
             to_days(cast(`v`.`date_started` as date))) as decimal(10, 0)) AS `Date Difference in Days`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 1) and
               (`vsatt`.`voided` = 0)))                                    AS `Visit Time`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 2) and
               (`vsatt`.`voided` = 0)))                                    AS `Visit Status`,
       cast((select `vsatt`.`value_reference`
             from `visit_attribute` `vsatt`
             where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 3) and
                    (`vsatt`.`voided` = 0))) as decimal(10, 0))            AS `Low Window`,
       cast((select `vsatt`.`value_reference`
             from `visit_attribute` `vsatt`
             where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 4) and
                    (`vsatt`.`voided` = 0))) as decimal(10, 0))            AS `Up Window`,
       cast((select `vsatt`.`value_reference`
             from `visit_attribute` `vsatt`
             where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 5) and
                    (`vsatt`.`voided` = 0))) as decimal(10, 0))            AS `Dose Number`,
       (select `vsatt`.`value_reference`
        from `visit_attribute` `vsatt`
        where ((`vsatt`.`visit_id` = `v`.`visit_id`) and (`vsatt`.`attribute_type_id` = 6) and
               (`vsatt`.`voided` = 0)))                                    AS `Vaccine`
from ((((`encounter` `enc` left join `location` `l` on ((`l`.`location_id` = `enc`.`location_id`))) left join `visit` `v` on ((`v`.`visit_id` = `enc`.`visit_id`))) left join `visit_type` `vtype` on ((`vtype`.`visit_type_id` = `v`.`visit_type_id`)))
         left join `visit_attribute` `vsatts` on ((`vsatts`.`visit_id` = `v`.`visit_id`)))
where ((`enc`.`visit_id` is not null) and (`enc`.`voided` = 0) and (`enc`.`encounter_type` = 10) and
       (`vsatts`.`attribute_type_id` = 3))
order by `enc`.`encounter_datetime` desc;
