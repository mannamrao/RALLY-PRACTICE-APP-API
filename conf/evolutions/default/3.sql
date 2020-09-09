# --- !Ups

create table "doctor_specialities" (
  "doctor_id" bigint references users("id"),
  "speciality_id" bigint not null references specialities("id")
);

INSERT INTO doctor_specialities(doctor_id, speciality_id)
	VALUES (1, 1),
	(2, 1),
	(3, 2),
	(3, 3),
	(4, 1),
	(5, 2);

# --- !Downs

drop table "doctor_specialities" if exists;
