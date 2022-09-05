DROP DATABASE IF EXISTS SuperHeroDB;

CREATE DATABASE SuperHeroDB;

USE SuperHeroDB;

CREATE TABLE `Organization`(
	organization_id INT AUTO_INCREMENT,
	organization_name varchar(50),
	organization_address varchar(50),
	organization_number varchar(20),
	CONSTRAINT pk_organization PRIMARY KEY(organization_id)
);

CREATE TABLE Power(
	power_id INT AUTO_INCREMENT,
	power_type varchar(30),
	power_description varchar(50),
    CONSTRAINT pk_power PRIMARY KEY(power_id)
);

CREATE TABLE Location(
	location_id INT AUTO_INCREMENT,
	location_name varchar(100),
	location_description varchar(150),
	location_address_information varchar(50),
	location_latitude double,
	location_longitude double,
	CONSTRAINT pk_location PRIMARY KEY(location_id)
);

CREATE TABLE SuperHero(
	superhero_id INT AUTO_INCREMENT,
	hero_name varchar(50),
	hero_description varchar(150),
	-- power_id INT,
	-- organization_id INT,
	CONSTRAINT pk_superhero PRIMARY KEY(superhero_id)
	-- CONSTRAINT fk_organization_superhero FOREIGN KEY (organization_id)
		-- REFERENCES `Organization`(organization_id),
	-- CONSTRAINT fk_power_superhero FOREIGN KEY (power_id)
		-- REFERENCES Power(power_id)
);

CREATE TABLE SuperHeroPowers(
	superhero_id INT,
    power_id INT,
    CONSTRAINT pk_superhero_powers PRIMARY KEY(superhero_id, power_id),
    CONSTRAINT fk_superhero_superhero_powers FOREIGN KEY(superhero_id)
		REFERENCES SuperHero(superhero_id),
	CONSTRAINT fk_power_superhero_powers FOREIGN KEY(power_id)
		REFERENCES Power(power_id)
);

-- Intermediate table to identify heroes to their orgs. A hero can be associated with multiple orgs.
CREATE TABLE SuperHeroOrganization(
	superhero_id INT,
    organization_id INT,
    CONSTRAINT pk_superhero_organization PRIMARY KEY(superhero_id, organization_id),
    CONSTRAINT fk_superhero_superhero_organization FOREIGN KEY(superhero_id)
		REFERENCES SuperHero(superhero_id),
	CONSTRAINT fk_organization_superhero_organization FOREIGN KEY(organization_id)
		REFERENCES `Organization`(organization_id)
);
CREATE TABLE SuperHeroLocation(
    superhero_location_id INT AUTO_INCREMENT,
	superhero_id INT,
	location_id INT,
	event_date varchar(9),
	CONSTRAINT pk_superhero_location PRIMARY KEY(superhero_location_id),
	CONSTRAINT fk_superhero_superhero_location FOREIGN KEY (superhero_id)
		REFERENCES SuperHero(superhero_id),
	CONSTRAINT fk_location_superhero_location FOREIGN KEY (location_id)
		REFERENCES Location(location_id)
);

CREATE TABLE SuperVillain(
	villain_id INT AUTO_INCREMENT,
	villain_name varchar(50),
	villain_description varchar(150),
	power_id INT,
	-- organization_id INT,
	CONSTRAINT pk_villain PRIMARY KEY(villain_id),
	CONSTRAINT fk_power_supervillain FOREIGN KEY (power_id)
		REFERENCES Power(power_id)
	-- CONSTRAINT fk_organization_supervillain FOREIGN KEY (organization_id)
		-- REFERENCES `Organization`(organization_id)
);

CREATE TABLE SuperVillainPowers(
	villain_id INT,
    power_id INT,
    CONSTRAINT pk_supervillain_powers PRIMARY KEY(villain_id, power_id),
    CONSTRAINT fk_supervillain_supervillain_powers FOREIGN KEY(villain_id)
		REFERENCES SuperVillain(villain_id),
	CONSTRAINT fk_power_supervillain_powers FOREIGN KEY(power_id)
		REFERENCES Power(power_id)
);

-- Intermediate table to identify villains to their orgs. A villain can be associated with multiple orgs.
CREATE TABLE SuperVillainOrganization(
	villain_id INT,
    organization_id INT,
    CONSTRAINT pk_supervilain_organization PRIMARY KEY(villain_id, organization_id),
    CONSTRAINT fk_supervillain_supervillain_organization FOREIGN KEY(villain_id)
		REFERENCES SuperVillain(villain_id),
	CONSTRAINT fk_organization_supervillain_organization FOREIGN KEY(organization_id)
		REFERENCES `Organization`(organization_id)
);

CREATE TABLE SuperVillainLocation(
    supervillain_location_id INT AUTO_INCREMENT,
	villain_id INT,
	location_id INT,
	event_date varchar(9),
	CONSTRAINT pk_supervillain_location PRIMARY KEY(supervillain_location_id),
	CONSTRAINT fk_supervillain_supervillain_location FOREIGN KEY (villain_id)
		REFERENCES SuperVillain(villain_id),
	CONSTRAINT fk_location_supervillain_location FOREIGN KEY (location_id)
		REFERENCES Location(location_id)
);

CREATE TABLE Member(
	member_id INT AUTO_INCREMENT,
	member_name varchar(50),
	member_title varchar(30),
	organization_id INT,
	CONSTRAINT pk_member PRIMARY KEY(member_id),
	CONSTRAINT fk_organization_member FOREIGN KEY (organization_id)
		REFERENCES `Organization`(organization_id)
);

INSERT INTO SuperHero(superhero_id, hero_name, hero_description) VALUES(1, "Superman", "A noble superhero hailing from the destroyed planet Krypton, living under the alias Clark Kent");
INSERT INTO `Organization`(organization_id, organization_name, organization_address, organization_number) VALUES(1, "BlackHawk", "1234 New Wark Drive NJ, 20133", "2024445671");
INSERT INTO `Organization`(organization_id, organization_name, organization_address, organization_number) VALUES(2, "MarvelHQ", "2234 Baskins Drive NY, 20133", "3019837778");
INSERT INTO Power(power_id, power_type, power_description) VALUES(1, "Flight", "Flying");
INSERT INTO Power(power_id, power_type, power_description) VALUES(2, "X-ray Vision", "See through objects");
INSERT INTO Power(power_id, power_type, power_description) VALUES(3, "Super Strength", "10x human strength"); 
INSERT INTO Power(power_id, power_type, power_description) VALUES(4, "Toxic Skin", "Dissolve tangible objects upon touch"); 
INSERT INTO Power(power_id, power_type, power_description) VALUES(5, "Heat Vision", "Dissolve tangible objects within line of sight"); 
INSERT INTO SuperHeroPowers(superhero_id, power_id) VALUES(1, 1);
INSERT INTO SuperHeroPowers(superhero_id, power_id) VALUES(1, 2);
INSERT INTO SuperHeroPowers(superhero_id, power_id) VALUES(1, 3);
INSERT INTO SuperHeroPowers(superhero_id, power_id) VALUES(1, 5);
INSERT INTO SuperHeroOrganization(superhero_id, organization_id) VALUES(1, 1);
INSERT INTO Location(location_id, location_name, location_description, location_address_information, location_latitude, location_longitude) VALUES(1, "NY Park", "Park in the middle of New York City", "2334 NY Coastal Park", 43.3, 21.2);
INSERT INTO Location(location_id, location_name, location_description, location_address_information, location_latitude, location_longitude) VALUES(2, "Sahara Dessert", "Desert", "Desert in Africa", -50, 30);
INSERT INTO Location(location_id, location_name, location_description, location_address_information, location_latitude, location_longitude) VALUES(3, "Restaurant", "McDonalds", "1111 Hoboken NJ", 60, 21.2);
INSERT INTO Location(location_id, location_name, location_description, location_address_information, location_latitude, location_longitude) VALUES(4, "Skate Center", "Skatepark in Missouri", "4563 Jibbersih Lane", 12.9, 2.6);
INSERT INTO SuperHeroLocation(superhero_location_id, superhero_id, location_id) VALUES(1, 1, 2);
INSERT INTO SuperHeroLocation(superhero_location_id, superhero_id, location_id) VALUES(2, 1, 4);
-- SELECT Power.power_id, Power.power_type, Power.power_description, SuperHeroPowers.superhero_id
-- FROM Power
-- JOIN SuperHeroPowers ON Power.power_id = SuperHeroPowers.power_id
-- WHERE SuperHeroPowers.superhero_id = 1;