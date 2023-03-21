SELECT DISTINCT p.name as professor_name, c.name as course_name
FROM professors p
JOIN department d ON p.department_id = d.id
JOIN course c ON c.department_id = d.id AND c.professor_id = p.id
JOIN schedule s ON s.course_id = c.id AND s.professor_id = p.id