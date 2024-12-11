INSERT INTO `usuarios` (`user`, `password`, `ROL`) VALUES
('user1', 'password123', 'USER'),
('admin1', 'adminpassword', 'ADMIN');


INSERT INTO `vuelos` (destino, plazas, fecha_salida, hora_salida, fecha_llegada, hora_llegada) VALUES
('Madrid', 150, '2024-12-15', '10:00:00', '2024-12-15', '12:00:00'),
('Barcelona', 200, '2024-12-20', '15:00:00', '2024-12-20', '17:30:00');

INSERT INTO `reservas` (usuario_id, vuelo_id) VALUES
(1, 1),
(2, 2);
