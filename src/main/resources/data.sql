INSERT INTO feedback_table (feedback_id, feedback_for, feedback_by, title, message, is_positive, created_at)
VALUES
    (RANDOM_UUID(), 'Mr. Rahman', 'student1@example.com', 'Good But Fast', 'Mr. Rahman has a remarkable gift for explaining concepts with clarity and enthusiasm, and he is deeply committed to fostering student success. His ability to convey complex material in an understandable manner is exceptional. However, the pace of the lectures could be slightly slower to allow for better comprehension and note-taking.', false, CURRENT_TIMESTAMP);

INSERT INTO feedback_table (feedback_id, feedback_for, feedback_by, title, message, is_positive, created_at)
VALUES
    (RANDOM_UUID(), 'Ms. Sultana', 'student2@example.com', 'Very Helpful', 'Finding a teacher as genuinely caring and attentive as Ms. Sultana is a rare and fortunate experience. Throughout the academic year, her willingness to help students succeed has stood out in countless ways. She goes beyond the classroom to ensure every student understands the material and feels supported in their learning journey.', true, CURRENT_TIMESTAMP);

INSERT INTO feedback_table (feedback_id, feedback_for, feedback_by, title, message, is_positive, created_at)
VALUES
    (RANDOM_UUID(), 'Ms. Rahima', 'student3@example.com', 'Engaging Class', 'Ms. Rahima possesses a unique talent for transforming her classroom into a vibrant, energetic, and welcoming space that motivates every student to participate actively. Her teaching style is far from monotonous; she incorporates interactive activities and real-world examples that make learning both fun and meaningful. Students feel valued and encouraged to express their thoughts.', true, CURRENT_TIMESTAMP);

INSERT INTO feedback_table (feedback_id, feedback_for, feedback_by, title, message, is_positive, created_at)
VALUES
    (RANDOM_UUID(), 'Dr Oisy', 'student4@example.com', 'Exceptional Mentor', 'Dr Oisy is an extraordinary educator who has fundamentally transformed my understanding of the subject matter. Her lectures are meticulously prepared, intellectually stimulating, and delivered with an infectious passion that inspires students to think critically and deeply. What truly sets her apart is her genuine commitment to student development - she is always available for discussions, provides constructive feedback on assignments, and creates an environment where questions are celebrated rather than discouraged. Her ability to bridge theory and practical application is remarkable, making complex concepts accessible and relevant. The enthusiasm she brings to every class session is palpable and contagious, motivating students to engage more thoroughly with the material. Dr Oisy does not merely teach; she mentors, guides, and empowers students to reach their full potential. She is undoubtedly one of the finest educators I have encountered.', true, CURRENT_TIMESTAMP);
INSERT INTO feedback_table (feedback_id, feedback_for, feedback_by, title, message, is_positive, created_at)
VALUES
    (RANDOM_UUID(), 'Dr. Test', 'abdullahhimel47', 'Test Feedback', 'Dr Oisy is an extraordinary educator who has fundamentally transformed my understanding of the subject matter. Her lectures are meticulously prepared, intellectually stimulating, and delivered with an infectious passion that inspires students to think critically and deeply. What truly sets her apart is her genuine commitment to student development - she is always available for discussions, provides constructive feedback on assignments, and creates an environment where questions are celebrated rather than discouraged. Her ability to bridge theory and practical application is remarkable, making complex concepts accessible and relevant. The enthusiasm she brings to every class session is palpable and contagious, motivating students to engage more thoroughly with the material.', true, CURRENT_TIMESTAMP);



-- user table

INSERT INTO user_table (user_id, username, password, role)
VALUES
    ('0bb5dffb-9912-4b63-bbef-d65c48359dcd','abdullahhimel46','$2a$10$XZsYJaYy47Q0pWNmMWGzN.FwyzvPnT0NPDnWmsbqHhPwSOpf0fOKS','ROLE_ADMIN');

INSERT INTO user_table (user_id, username, password, role)
VALUES
    ('56f9ab46-fe0e-488e-8838-72c746dabe1e','abdullahhimel47','$2a$10$w35pkEhQmJ6B1K4h/1HraOznyH6sLtxboDbPdTSvp1JyhXC26J2h6','ROLE_USER');

