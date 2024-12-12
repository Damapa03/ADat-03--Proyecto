INSERT INTO `usuarios` (`username`, `password`, `roles`) VALUES ('user1', '$2a$10$TJvKpccWn/Z4F3wb5LezBeaWCWQCeIaAfeppE7hYHtpgVrahmfEim', 'USER'), ('admin1', '$2a$10$nvEvcvaTqNXIrDAHE7E8ueeQ9GJQRdV2yACr1nXS/icsdveCGayTK', 'ADMIN');

INSERT INTO `vuelos` (destino, plazas, fecha_salida, hora_salida, fecha_llegada, hora_llegada) VALUES ('Madrid', 150, '2024-12-15', '10:00:00', '2024-12-15', '12:00:00'), ('Barcelona', 200, '2024-12-20', '15:00:00', '2024-12-20', '17:30:00');

INSERT INTO `reservas` (usuario_id, vuelo_id) VALUES (1, 1), (2, 2);
