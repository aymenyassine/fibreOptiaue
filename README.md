# Fibre Optique – API de Gestion de Réseau FTTH

API REST Spring Boot pour la gestion d'un réseau de fibre optique : datacenters, répartiteurs, équipements, splitters, boîtes clients et chemins de fibre, avec authentification JWT et gestion des rôles.

## Fonctionnalités

- Authentification (inscription/connexion) avec génération de token JWT
- CRUD complet sur les ressources réseau : datacenters, répartiteurs, équipements, splitters, boîtes clients, chemins de fibre
- Gestion des rôles ADMIN / TECHNICIEN avec contrôle d'accès par endpoint
- Documentation complète des endpoints dans `API_DOCUMENTATION.md`

## Stack technique

- Java, Spring Boot
- Spring Data JPA, Spring Security, JWT
- Maven

## Lancer le projet

```bash
./mvnw spring-boot:run
```

## Documentation

Voir `API_DOCUMENTATION.md` pour la liste complète des endpoints et des exemples de requêtes.

## Contexte

Version API du projet de gestion de réseau fibre optique réalisé à l'EMSI Casablanca. Une version plus avancée avec cartographie géospatiale et frontend est disponible ici : [fibre_oprtique_pfa](https://github.com/aymenyassine/fibre_oprtique_pfa).

