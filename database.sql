INSERT INTO public.users(id_user, username, "password", user_role)
	VALUES (default, 'mihaighise', '1234', 'USER'),
	(default, 'admin', 'admin', 'ADMIN'),
	(default, 'raducaprita', '12345', 'USER');
	
	
INSERT INTO public.devices(id, description, address, maximum_energy, id_user)
	VALUES (default, 'description 1', 'bucuresti', 134, 1),
	(default, 'description 2', 'sibiu', 156, 3),
	(default, 'description 3', 'timisoara', 178, null),
	(default, 'description 4', 'cluj', 190, null),
	(default, 'description 5', 'salaj', 200, 1);
	
INSERT INTO public.timestamp(id_timestamp, id_device, "time", consumption) 
	VALUES (default, 1, '20221025 17:00', 10),
	(default, 1, '20221025 18:00', 15),
	(default, 1, '20221025 19:00', 16),
	(default, 1, '20221025 20:00', 25),
	(default, 1, '20221025 21:00', 49),
	(default, 1, '20221025 22:00', 56),
	(default, 1, '20221025 23:00', 78),
	(default, 1, '20221026 00:00', 100),
	(default, 1, '20221026 01:00', 125),
	(default, 5, '20221025 22:00', 10),
	(default, 5, '20221025 23:00', 40),
	(default, 5, '20221027 08:00', 105);