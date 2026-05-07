# Documentation de l'API Fibre Optique

Cette documentation liste l'ensemble des points d'accès (endpoints) de l'API REST du Backend. 
> **Note :** La majorité des endpoints nécessitent une authentification via un token JWT (à passer dans l'entête `Authorization: Bearer <votre_token>`).

## Variables globales
- **Base URL** : `http://localhost:8080` (modifiez le port selon votre configuration).

---

## 1. Authentification (`/api/auth`)

### Enregistrement d'un utilisateur
- **URL** : `POST /api/auth/register`
- **Authentification** : Aucune
- **Body (JSON)** :
  ```json
  {
    "nom": "Jean Dupont",
    "email": "jean.dupont@email.com",
    "password": "motdepassesecurise",
    "role": "ADMIN" // ou "THECNICIEN"
  }
  ```
- **Réponse (200 OK)** : Retourne le token JWT généré.
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

### Connexion (Login)
- **URL** : `POST /api/auth/authenticate`
- **Authentification** : Aucune
- **Body (JSON)** :
  ```json
  {
    "email": "jean.dupont@email.com",
    "password": "motdepassesecurise"
  }
  ```
- **Réponse (200 OK)** : Retourne le token JWT.

---

## 2. API Standard (CRUD)

Toutes les entités suivantes disposent des mêmes 5 opérations standard (remplacez `<entite>` par la ressource désirée).

### Les Ressources (Endpoints de base)
- Datacenters : `/api/datacenters`
- Répartiteurs : `/api/repartiteurs`
- Équipements : `/api/equipements`
- Splitters : `/api/splitters`
- Boîtes Clients : `/api/boite-clients`
- Chemins de Fibre : `/api/chemin-fibres`
- Utilisateurs (Gestion) : `/api/utilisateurs`

### A. Créer un élément
- **URL** : `POST /api/<entite>`
- **Authentification** : Requise (`ADMIN`)
- **Body** : L'objet Request DTO correspondant (voir structure des modèles ci-dessous).
- **Réponse** : `201 Created`

### B. Obtenir tous les éléments
- **URL** : `GET /api/<entite>`
- **Authentification** : Requise (`ADMIN` ou `THECNICIEN`)
- **Réponse** : `200 OK` (Liste d'objets Response DTO)

### C. Obtenir un élément par ID
- **URL** : `GET /api/<entite>/{id}`
- **Authentification** : Requise (`ADMIN` ou `THECNICIEN`)
- **Réponse** : `200 OK` (L'objet Response DTO unique)

### D. Mettre à jour un élément
- **URL** : `PUT /api/<entite>/{id}`
- **Authentification** : Requise (`ADMIN`)
- **Body** : L'objet Request DTO mis à jour.
- **Réponse** : `200 OK`

### E. Supprimer un élément
- **URL** : `DELETE /api/<entite>/{id}`
- **Authentification** : Requise (`ADMIN`)
- **Réponse** : `204 No Content`

---

## 3. Structure des objets (Request DTOs) à envoyer

### Datacenter
```json
{
  "nom": "Datacenter Principal",
  "capacite": 1000,
  "latitude": 48.8566,
  "longitude": 2.3522
}
```

### Répartiteur
```json
{
  "nom": "Répartiteur Nord",
  "nbPorts": 48,
  "datacenterId": 1
}
```

### Équipement
```json
{
  "ip": "192.168.1.10",
  "status": "ACTIF", 
  "type": "ROUTEUR", 
  "repartiteurId": 1
}
```

### Splitter
```json
{
  "ratio": "UN_SUR_16", 
  "nbSortie": 4,
  "repartiteurId": 1
}
```

### Boite Client
```json
{
  "nom": "Boite Résidence Les Lilas",
  "nbPorts": 8,
  "portsUtilises": 2,
  "latitude": 48.8600,
  "longitude": 2.3600,
  "splitterId": 1
}
```

### Chemin Fibre
```json
{
  "source": "Datacenter 1",
  "destination": "Répartiteur A",
  "longueur": 12.5,
  "typeFibre": "Monomode",
  "statut": "FONCTIONNEL"
}
```

---

## 4. Valeurs des Énumérations (Enums)

Pour éviter les erreurs côté frontend, voici les listes exactes des valeurs autorisées pour les champs de type `Enum` :

### `Role` (pour Utilisateur)
- `"ADMIN"`
- `"THECNICIEN"`

### `Status` (pour Équipement)
- `"ACTIF"`
- `"INACTIF"`

### `Type` (pour Équipement)
- `"OLT"`
- `"SWITCH"`
- `"ROUTEUR"`

### `Ratio` (pour Splitter)
- `"UN_SUR_8"`
- `"UN_SUR_16"`
- `"UN_SUR_32"`
