

INSERT INTO insurance (id, policy_number, provider, expiry, created_at, modified_at) VALUES
  ('2b4d6f81-a3c5-4e7f-9b2d-3c5e7f9b1a23', 'PN00000004', 'SBI General',   '2029-01-15', '2026-01-16T10:15:00', '2026-01-16T10:15:00'),
  ('1a3c5e7f-9b2d-4c6e-8f10-2a4b6c8d0e12', 'PN00000003', 'HDFC Ergo',     '2027-12-31', '2026-01-16T10:10:00', '2026-01-16T10:10:00'),
  ('5e7f91a3-d6f8-7182-c5e7-6f91b4d65f56', 'PN00000007', 'SBI General',   '2030-03-31', '2026-01-16T10:30:00', '2026-01-16T10:30:00'),
  ('7182a3c5-f9b2-93d4-e7f9-81a3d6f88278', 'PN00000009', 'HDFC Ergo',     '2028-11-30', '2026-01-16T10:40:00', '2026-01-16T10:40:00'),
  ('81a3b4c5-0ab2-a4d5-f9b2-93d4e7f99389', 'PN00000010', 'ICICI Lombard', '2029-07-31', '2026-01-16T10:45:00', '2026-01-16T10:45:00');

INSERT INTO patients (id, name, dob, gender, email, blood_group, insurance_id, created_at, modified_at) VALUES
  ('a1d3b1a7-6a4d-4b9e-8a1e-2a5c8f9d1e01', 'Aarav Mehta',    '1990-01-15', 'MALE',       'aarav.mehta@example.com',    'A_POSITIVE',  NULL,                                   '2026-01-16 21:25:10', '2026-01-16 21:25:10'),
  ('b2e4c2b8-7b5e-4c0f-9b2f-3b6d9e0f2e02', 'Isha Nair',      '1992-03-22', 'FEMALE',     'isha.nair@example.com',      'A_NEGATIVE',  '7182a3c5-f9b2-93d4-e7f9-81a3d6f88278', '2026-01-16 21:25:11', '2026-01-16 21:25:11'),
  ('c3f5d3c9-8c6f-4d10-ac30-4c7eaf103f03', 'Rahul Kapoor',   '1988-07-09', 'MALE',       'rahul.kapoor@example.com',   'B_POSITIVE',  '81a3b4c5-0ab2-a4d5-f9b2-93d4e7f99389', '2026-01-16 21:25:12', '2026-01-16 21:25:12'),
  ('d406e4da-9d70-4e21-bd41-5d8fb0214a04', 'Neha Sharma',    '1995-11-30', 'NON_BINARY', 'neha.sharma@example.com',    'B_NEGATIVE',  NULL,                                   '2026-01-16 21:25:13', '2026-01-16 21:25:13'),
  ('e517f5eb-ae81-4f32-ce52-6e90c1325b05', 'Vihaan Rao',     '1993-05-17', 'MALE',       'vihaan.rao@example.com',     'AB_POSITIVE', '5e7f91a3-d6f8-7182-c5e7-6f91b4d65f56', '2026-01-16 21:25:14', '2026-01-16 21:25:14'),
  ('f62806fc-bf92-5043-df63-7fa1d2436c06', 'Sara Qureshi',   '1998-12-02', 'FEMALE',     'sara.qureshi@example.com',   'AB_NEGATIVE', NULL,                                   '2026-01-16 21:25:15', '2026-01-16 21:25:15'),
  ('07a9170d-d0a3-5144-e074-80b2e3547d07', 'Kabir Desai',    '1985-08-24', 'MALE',       'kabir.desai@example.com',    'O_POSITIVE',  '1a3c5e7f-9b2d-4c6e-8f10-2a4b6c8d0e12', '2026-01-16 21:25:16', '2026-01-16 21:25:16'),
  ('18ba281e-e1b4-5255-f185-91c3f4658e08', 'Ananya Joshi',   '2000-06-11', 'NON_BINARY', 'ananya.joshi@example.com',   'O_NEGATIVE',  NULL,                                   '2026-01-16 21:25:17', '2026-01-16 21:25:17'),
  ('29cb392f-f2c5-5366-0196-a2d405769f09', 'Devika Menon',   '1991-09-05', 'OTHER',      'devika.menon@example.com',   'O_POSITIVE',  NULL,                                   '2026-01-16 21:25:18', '2026-01-16 21:25:18'),
  ('3adc4a30-03d6-5477-12a7-b3e51687a00a', 'Yashwant Singh', '1989-02-18', 'OTHER',      'yashwant.singh@example.com', 'A_POSITIVE',  '2b4d6f81-a3c5-4e7f-9b2d-3c5e7f9b1a23', '2026-01-16 21:25:19', '2026-01-16 21:25:19');

INSERT INTO doctors (id, name, specialization, email, created_at, modified_at) VALUES
  ('90b1a3f2-6d9c-4c3f-9a1e-1c2d3e4f5a10', 'Dr. Arjun Mehta',    'CARDIOLOGY',  'arjun.mehta@apollum.hospital',    '2026-01-16 21:32:01', '2026-01-16 21:32:01'),
  ('a1c2d3e4-f5a6-47b8-9123-4567890abc11', 'Dr. Nisha Rao',      'NEUROLOGY',   'nisha.rao@apollum.hospital',      '2026-01-16 21:32:02', '2026-01-16 21:32:02'),
  ('b2d3e4f5-a6b7-48c9-8234-5678901bcd22', 'Dr. Karan Shah',     'GYNECOLOGY',  'karan.shah@apollum.hospital',     '2026-01-16 21:32:03', '2026-01-16 21:32:03'),
  ('c3e4f5a6-b7c8-49da-9345-6789012cde33', 'Dr. Priya Menon',    'GENERAL',     'priya.menon@apollum.hospital',    '2026-01-16 21:32:04', '2026-01-16 21:32:04'),
  ('d4f5a6b7-c8d9-4aeb-a456-7890123def44', 'Dr. Rohit Kapoor',   'DERMATOLOGY', 'rohit.kapoor@apollum.hospital',   '2026-01-16 21:32:05', '2026-01-16 21:32:05'),
  ('e5a6b7c8-d9e0-4bfc-b567-8901234ef055', 'Dr. Anika Bose',     'DERMATOLOGY', 'anika.bose@apollum.hospital',     '2026-01-16 21:32:06', '2026-01-16 21:32:06'),
  ('f6b7c8d9-e0f1-4c0d-c678-9012345f0016', 'Dr. Vivek Iyer',     'CARDIOLOGY',  'vivek.iyer@apollum.hospital',     '2026-01-16 21:32:07', '2026-01-16 21:32:07'),
  ('07c8d9ea-f102-4d1e-d789-012345600117', 'Dr. Meera Kulkarni', 'GYNECOLOGY',  'meera.kulkarni@apollum.hospital', '2026-01-16 21:32:08', '2026-01-16 21:32:08'),
  ('18d9ea0b-0123-4e2f-e890-123456700128', 'Dr. Salman Qureshi', 'NEUROLOGY',   'salman.qureshi@apollum.hospital', '2026-01-16 21:32:09', '2026-01-16 21:32:09'),
  ('29ea0b1c-1234-4f30-f901-234567800139', 'Dr. Aisha Siddiqui', 'GENERAL',     'aisha.siddiqui@apollum.hospital', '2026-01-16 21:32:10', '2026-01-16 21:32:10');

INSERT INTO appointments
  (id, start_time, end_time, reason, status, doctor_id, patient_id, created_at, modified_at)
VALUES
  ('a0f1b2c3-d4e5-46f7-98a1-b2c3d4e5f601', '2026-01-17 09:00:00', '2026-01-17 09:30:00', 'Cardiology consultation',      'SCHEDULED',   '90b1a3f2-6d9c-4c3f-9a1e-1c2d3e4f5a10', 'a1d3b1a7-6a4d-4b9e-8a1e-2a5c8f9d1e01', '2026-01-16 21:38:30', '2026-01-16 21:38:30'),
  ('b1f2c3d4-e5f6-47a8-09b1-c2d3e4f5a602', '2026-01-17 10:00:00', '2026-01-17 10:45:00', 'Neurological evaluation',      'IN_PROGRESS', 'a1c2d3e4-f5a6-47b8-9123-4567890abc11', 'b2e4c2b8-7b5e-4c0f-9b2f-3b6d9e0f2e02', '2026-01-16 21:38:31', '2026-01-16 21:38:31'),
  ('c2f3d4e5-f6a7-48b9-10c2-d3e4f5a6b703', '2026-01-17 11:00:00', '2026-01-17 11:30:00', 'Gynecology follow-up',         'SCHEDULED',   'b2d3e4f5-a6b7-48c9-8234-5678901bcd22', 'c3f5d3c9-8c6f-4d10-ac30-4c7eaf103f03', '2026-01-16 21:38:32', '2026-01-16 21:38:32'),
  ('d3f4e5f6-0a1b-49ca-21d3-e4f5a6b7c804', '2026-01-17 12:00:00', '2026-01-17 12:20:00', 'General checkup',              'CANCELLED',   'c3e4f5a6-b7c8-49da-9345-6789012cde33', 'd406e4da-9d70-4e21-bd41-5d8fb0214a04', '2026-01-16 21:38:33', '2026-01-16 21:38:33'),
  ('e4f5a6b7-1b2c-4adb-32e4-f5a6b7c8d905', '2026-01-17 14:00:00', '2026-01-17 14:30:00', 'Dermatology consultation',     'COMPLETED',   'd4f5a6b7-c8d9-4aeb-a456-7890123def44', 'e517f5eb-ae81-4f32-ce52-6e90c1325b05', '2026-01-16 21:38:34', '2026-01-16 21:38:34'),
  ('f5a6b7c8-2c3d-4bec-43f5-a6b7c8d9ea06', '2026-01-17 15:00:00', '2026-01-17 15:40:00', 'Dermatology follow-up',        'COMPLETED',   'e5a6b7c8-d9e0-4bfc-b567-8901234ef055', 'f62806fc-bf92-5043-df63-7fa1d2436c06', '2026-01-16 21:38:35', '2026-01-16 21:38:35'),
  ('06a7b8c9-3d4e-4cfd-54f6-b7c8d9ea0b07', '2026-01-17 16:00:00', '2026-01-17 16:20:00', 'Cardiology diagnostic review', 'SCHEDULED',   'f6b7c8d9-e0f1-4c0d-c678-9012345f0016', '07a9170d-d0a3-5144-e074-80b2e3547d07', '2026-01-16 21:38:36', '2026-01-16 21:38:36'),
  ('17b8c9da-4e5f-4d0e-65f7-c8d9ea0b1c08', '2026-01-18 10:00:00', '2026-01-18 10:30:00', 'Gynecology checkup',           'SCHEDULED',   '07c8d9ea-f102-4d1e-d789-012345600117', '18ba281e-e1b4-5255-f185-91c3f4658e08', '2026-01-16 21:38:37', '2026-01-16 21:38:37'),
  ('28c9dade-5f60-4e1f-76f8-d9ea0b1c2d09', '2026-01-18 11:00:00', '2026-01-18 11:25:00', 'Neurology consultation',       'CANCELLED',   '18d9ea0b-0123-4e2f-e890-123456700128', '29cb392f-f2c5-5366-0196-a2d405769f09', '2026-01-16 21:38:38', '2026-01-16 21:38:38'),
  ('39dadedf-6071-4f20-87f9-ea0b1c2d3e10', '2026-01-18 12:00:00', '2026-01-18 12:30:00', 'General ENT consultation',     'SCHEDULED',   '29ea0b1c-1234-4f30-f901-234567800139', '3adc4a30-03d6-5477-12a7-b3e51687a00a', '2026-01-16 21:38:39', '2026-01-16 21:38:39');

INSERT INTO departments (id, name, head_doctor_id, created_at, modified_at) VALUES
  ('11111111-aaaa-4b11-9111-aaaaaaaaaaa1', 'Cardiology',       '90b1a3f2-6d9c-4c3f-9a1e-1c2d3e4f5a10', '2026-01-16 21:45:01', '2026-01-16 21:45:01'),
  ('22222222-bbbb-4b22-9222-bbbbbbbbbbb2', 'Neurology',        'a1c2d3e4-f5a6-47b8-9123-4567890abc11', '2026-01-16 21:45:02', '2026-01-16 21:45:02'),
  ('33333333-cccc-4b33-9333-ccccccccccc3', 'Gynecology',       '07c8d9ea-f102-4d1e-d789-012345600117', '2026-01-16 21:45:03', '2026-01-16 21:45:03'),
  ('44444444-dddd-4b44-9444-ddddddddddd4', 'Dermatology',      'd4f5a6b7-c8d9-4aeb-a456-7890123def44', '2026-01-16 21:45:04', '2026-01-16 21:45:04'),
  ('55555555-eeee-4b55-9555-eeeeeeeeeee5', 'General Medicine', 'c3e4f5a6-b7c8-49da-9345-6789012cde33', '2026-01-16 21:45:05', '2026-01-16 21:45:05');

INSERT INTO department_doctor (department_id, doctor_id) VALUES
  ('11111111-aaaa-4b11-9111-aaaaaaaaaaa1', '90b1a3f2-6d9c-4c3f-9a1e-1c2d3e4f5a10'), -- Dr. Arjun Mehta (Head)
  ('11111111-aaaa-4b11-9111-aaaaaaaaaaa1', 'f6b7c8d9-e0f1-4c0d-c678-9012345f0016'); -- Dr. Vivek Iyer
INSERT INTO department_doctor (department_id, doctor_id) VALUES
  ('22222222-bbbb-4b22-9222-bbbbbbbbbbb2', 'a1c2d3e4-f5a6-47b8-9123-4567890abc11'), -- Dr. Nisha Rao (Head)
  ('22222222-bbbb-4b22-9222-bbbbbbbbbbb2', '18d9ea0b-0123-4e2f-e890-123456700128'); -- Dr. Salman Qureshi
INSERT INTO department_doctor (department_id, doctor_id) VALUES
  ('33333333-cccc-4b33-9333-ccccccccccc3', '07c8d9ea-f102-4d1e-d789-012345600117'), -- Dr. Meera Kulkarni (Head)
  ('33333333-cccc-4b33-9333-ccccccccccc3', 'b2d3e4f5-a6b7-48c9-8234-5678901bcd22'); -- Dr. Karan Shah
INSERT INTO department_doctor (department_id, doctor_id) VALUES
  ('44444444-dddd-4b44-9444-ddddddddddd4', 'd4f5a6b7-c8d9-4aeb-a456-7890123def44'), -- Dr. Rohit Kapoor (Head)
  ('44444444-dddd-4b44-9444-ddddddddddd4', 'e5a6b7c8-d9e0-4bfc-b567-8901234ef055'); -- Dr. Anika Bose
INSERT INTO department_doctor (department_id, doctor_id) VALUES
  ('55555555-eeee-4b55-9555-eeeeeeeeeee5', 'c3e4f5a6-b7c8-49da-9345-6789012cde33'), -- Dr. Priya Menon (Head)
  ('55555555-eeee-4b55-9555-eeeeeeeeeee5', '29ea0b1c-1234-4f30-f901-234567800139'); -- Dr. Aisha Siddiqui

INSERT INTO accounts (id, username, password, role) VALUES
    ('11111111-1111-1111-1111-111111111111', 'patient', '{bcrypt}$2a$10$Pxb94GprTpyinMPidOpNze84JJxaTF91gnNQRJalV9PLwfk4vECmG', 'PATIENT'),
    ('22222222-2222-2222-2222-222222222222', 'doctor', '{bcrypt}$2a$10$Pxb94GprTpyinMPidOpNze84JJxaTF91gnNQRJalV9PLwfk4vECmG', 'DOCTOR'),
    ('33333333-3333-3333-3333-333333333333', 'admin', '{bcrypt}$2a$10$Pxb94GprTpyinMPidOpNze84JJxaTF91gnNQRJalV9PLwfk4vECmG', 'ADMIN');
