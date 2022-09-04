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
	location_lattitude double,
	location_longitude double,
	CONSTRAINT pk_location PRIMARY KEY(location_id)
);

CREATE TABLE SuperHero(
	superhero_id INT AUTO_INCREMENT,
	hero_name varchar(50),
	hero_description varchar(150),
	power_id INT,
	-- organization_id INT,
	CONSTRAINT pk_superhero PRIMARY KEY(superhero_id),
	-- CONSTRAINT fk_organization_superhero FOREIGN KEY (organization_id)
		-- REFERENCES `Organization`(organization_id),
	CONSTRAINT fk_power_superhero FOREIGN KEY (power_id)
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