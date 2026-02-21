INSERT INTO country(id,name,code) VALUES (1,'Colombia','CO') ON CONFLICT DO NOTHING;
INSERT INTO holiday_type(id,name,mode) VALUES
 (1,'Fijo',1),
 (2,'Puente festivo',2),
 (3,'Basado en Pascua',3),
 (4,'Pascua + Puente',4)
 ON CONFLICT DO NOTHING;
