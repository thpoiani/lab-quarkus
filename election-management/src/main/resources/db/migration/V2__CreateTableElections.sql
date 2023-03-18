CREATE TABLE elections (
  id VARCHAR(40) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id));

CREATE TABLE election_candidate (
  election_id VARCHAR(40) NOT NULL,
  candidate_id VARCHAR(40) NOT NULL,
  votes INTEGER DEFAULT 0,
PRIMARY KEY (election_id, candidate_id));

-- SEED: mockaroo.com
INSERT INTO candidates (id, photo, given_name, family_name, email, phone, job_title) VALUES
('968984fb-441a-4fa8-875d-25d640bcc7cf', 'https://robohash.org/voluptatemquiaexcepturi.png', 'Ricky', 'Endricci', 'rendricci0@cbslocal.com', null, null),
('7b05d6be-540b-4f3e-b057-15667a71b551', 'https://robohash.org/isteexercitationemconsequatur.png', 'Francklin', 'Leivers', 'fleivers1@stumbleupon.com', '702-218-9762', null),
('0383f511-fa58-4269-ba32-b5bbee0939e2', 'https://robohash.org/autautquod.png', 'Gorden', 'Habergham', 'ghabergham2@europa.eu', '868-308-3712', 'General Manager'),
('22638b6c-7871-41f4-bb84-22208035b135', 'https://robohash.org/nonquiseum.png', 'Blake', 'Bothams', 'bbothams3@cafepress.com', null, 'Account Executive'),
('4959f6e5-e6ee-44f4-a0ce-fe85e02ef300', 'https://robohash.org/optioipsamid.png', 'Aarika', 'Dalmon', 'adalmon4@bing.com', null, 'Civil Engineer'),
('db04b3c1-7d28-46c0-8654-c0e970b560ab', 'https://robohash.org/distinctioundeneque.png', 'Charmine', 'Kerford', 'ckerford5@seattletimes.com', null, 'Cost Accountant'),
('ee4647af-1330-4033-aaf4-58bbf25689fa', 'https://robohash.org/aspernaturquasea.png', 'Conway', 'Nation', 'cnation6@clickbank.net', null, 'Payment Adjustment Coordinator'),
('7d125c02-edce-4f8b-b63e-6c12648cf8ca', 'https://robohash.org/minimanobisoptio.png', 'Abran', 'McGinty', 'amcginty7@elpais.com', null, 'Quality Engineer'),
('a7d365f6-e823-4e90-bbf8-a7b72ff44b4f', 'https://robohash.org/doloremquecommodiquisquam.png', 'Vannie', 'Goodin', 'vgoodin8@cbslocal.com', null, 'Compensation Analyst'),
('4b9b09f1-7ec8-48bb-874d-b92c724d5f9e', 'https://robohash.org/autcorruptirepudiandae.png', 'Trudi', 'Longson', 'tlongson9@ucla.edu', '263-487-4046', 'Electrical Engineer'),
('cce35646-db92-4630-8614-0eb1f9d13e9c', 'https://robohash.org/quisdistinctioaut.png', 'Feodora', 'Silversmidt', 'fsilversmidta@shinystat.com', '290-452-5058', 'Junior Executive'),
('802640e3-c987-41f9-9ac7-2e9a7df422a6', null, 'Ninnetta', 'Cutteridge', 'ncutteridgeb@typepad.com', null, 'Nurse'),
('9fc8f255-8421-4fc6-ae0d-a5aa59d9b8c3', 'https://robohash.org/laudantiumdeleniticommodi.png', 'Merrick', 'Ojeda', 'mojedac@linkedin.com', '994-571-8956', 'Graphic Designer'),
('35eeb3f3-e675-45f7-a3c8-cb5dc707242e', 'https://robohash.org/utquoaut.png', 'Sibella', 'Charley', 'scharleyd@unesco.org', '687-944-3798', 'Assistant Media Planner'),
('86232ee5-a6df-485e-9299-4ee134fadb46', 'https://robohash.org/laborumvelasperiores.png', 'Joachim', 'Galletley', 'jgalletleye@addtoany.com', null, 'VP Product Management'),
('5a8e81ac-516c-4732-9238-c3959f2c8fc8', 'https://robohash.org/autquisquameos.png', 'Dominga', 'Allenson', 'dallensonf@sfgate.com', '282-211-4984', null),
('d7168431-4b3c-4e2f-b30a-fb580988de0a', 'https://robohash.org/dolordictadolor.png', 'Dell', 'Pock', 'dpockg@tiny.cc', null, 'Civil Engineer'),
('57164a54-0360-4756-b282-21588b184ced', null, 'Fletcher', 'Glackin', 'fglackinh@hostgator.com', '814-320-0802', 'Budget/Accounting Analyst II'),
('bd2cc59c-f07c-4d7b-9bd5-60dc7b780777', 'https://robohash.org/eligendimollitiaaut.png', 'Kassey', 'Kuhnt', 'kkuhnti@unesco.org', null, null),
('ac7647a4-2cd5-4cbd-897a-d53b4966a3c2', 'https://robohash.org/auteosesse.png', 'Robinette', 'Bliven', 'rblivenj@ehow.com', null, 'Nuclear Power Engineer');
