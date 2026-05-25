INSERT INTO membership_plans
(plan_type, duration_days, price, active)
VALUES
('MONTHLY', 30, 299.00, true),
('QUARTERLY', 90, 799.00, true),
('YEARLY', 365, 2499.00, true);
ON CONFLICT (plan_type) DO NOTHING;

INSERT INTO membership_tiers
(tier_type, rank, free_delivery, discount_percent,
 priority_support, early_access, criteria_json)
VALUES
('SILVER', 1, true, 5, false, true,
 '{"minOrderCount":0}'),
('GOLD', 2, true, 10, true, true,
 '{"minOrderCount":10,"minOrderValue":5000}'),

('PLATINUM', 3, true, 15, true, true,
 '{"minOrderCount":25,"minOrderValue":15000}');
 ON CONFLICT (tier_type) DO NOTHING;