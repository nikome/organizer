INSERT INTO public.roles (id, name)
VALUES
(1,'USER'),
(2,'ADMIN'),
(3, 'ROLE_USER'),
(4, 'USER_ROLE')

DELETE FROM public.roles WHERE id > 0