# Plan de Sprints - JavaTown Library

## Sprint 1 : Foundation & Authentication (2 semaines)

### User Stories
- **US-1.1** : En tant qu'utilisateur, je veux pouvoir me connecter au système pour accéder aux fonctionnalités
- **US-1.2** : En tant qu'employé, je veux pouvoir me connecter avec des privilèges administrateur
- **US-1.3** : En tant que client, je veux pouvoir créer un compte pour emprunter des documents

### Tasks
#### Back-end
- [ ] Créer les controllers pour l'authentification (AuthController)
- [ ] Implémenter JWT security configuration
- [ ] Créer les endpoints POST /api/auth/login et /api/auth/register
- [ ] Créer UserService avec méthodes d'authentification
- [ ] Ajouter validation des données d'entrée
- [ ] Tests unitaires pour AuthController et UserService

#### Front-end
- [ ] Configurer Tailwind CSS proprement
- [ ] Créer les composants Login et Register
- [ ] Créer AuthContext pour gérer l'état d'authentification
- [ ] Implémenter le routing avec React Router
- [ ] Créer les pages principales (Home, Login, Register)
- [ ] Intégrer les appels API pour l'authentification

---

## Sprint 2 : Document Management (2 semaines)

### User Stories
- **US-2.1** : En tant qu'employé, je veux pouvoir ajouter de nouveaux documents (Livres, CD, DVD)
- **US-2.2** : En tant qu'utilisateur, je veux pouvoir consulter le catalogue de documents
- **US-2.3** : En tant qu'utilisateur, je veux pouvoir rechercher des documents par titre, auteur, etc.
- **US-2.4** : En tant qu'employé, je veux pouvoir modifier les informations d'un document

### Tasks
#### Back-end
- [ ] Créer DocumentController avec CRUD complet
- [ ] Endpoints GET /api/documents (avec pagination et filtres)
- [ ] Endpoint POST /api/documents pour création
- [ ] Endpoint PUT /api/documents/{id} pour modification
- [ ] Endpoint DELETE /api/documents/{id}
- [ ] Implémenter recherche avec Spring Data JPA
- [ ] Ajouter validation métier et gestion d'erreurs
- [ ] Tests d'intégration

#### Front-end
- [ ] Créer composants DocumentCard, DocumentList
- [ ] Page Catalog avec affichage des documents
- [ ] Composant SearchBar avec filtres
- [ ] Modal AddDocument (pour employés)
- [ ] Modal EditDocument (pour employés)
- [ ] Pagination du catalogue
- [ ] Gestion des états de chargement et erreurs

---

## Sprint 3 : Loan Management (2 semaines)

### User Stories
- **US-3.1** : En tant que client, je veux pouvoir emprunter un document disponible
- **US-3.2** : En tant que client, je veux voir mes emprunts en cours
- **US-3.3** : En tant qu'employé, je veux pouvoir traiter les retours de documents
- **US-3.4** : En tant qu'employé, je veux voir tous les emprunts en cours

### Tasks
#### Back-end
- [ ] Créer LoanController avec endpoints complets
- [ ] POST /api/loans pour créer un emprunt
- [ ] GET /api/loans/customer/{id} pour emprunts d'un client
- [ ] PUT /api/loans/{id}/return pour retour
- [ ] Implémenter LoanService avec logique métier
- [ ] Calculer dates de retour selon type de document
- [ ] Vérifier disponibilité avant emprunt
- [ ] Tests unitaires et d'intégration

#### Front-end
- [ ] Bouton "Emprunter" sur DocumentCard
- [ ] Page MyLoans pour les clients
- [ ] Page LoansManagement pour employés
- [ ] Composant LoanCard avec infos d'emprunt
- [ ] Modal de confirmation d'emprunt
- [ ] Modal de retour de document
- [ ] Indicateurs visuels (en retard, bientôt dû)

---

## Sprint 4 : Fine Management & Notifications (2 semaines)

### User Stories
- **US-4.1** : En tant que système, je veux calculer automatiquement les amendes pour retards
- **US-4.2** : En tant que client, je veux voir mes amendes en cours
- **US-4.3** : En tant qu'employé, je veux pouvoir encaisser les amendes
- **US-4.4** : En tant que client, je veux être notifié des échéances proches

### Tasks
#### Back-end
- [ ] Créer FineController et FineService
- [ ] Scheduler pour calcul automatique des amendes
- [ ] Endpoint GET /api/fines/customer/{id}
- [ ] Endpoint PUT /api/fines/{id}/pay
- [ ] Logique de calcul des amendes (0.25€/jour)
- [ ] Service de notification par email
- [ ] Tests du scheduler et calculs

#### Front-end
- [ ] Page MyFines pour les clients
- [ ] Composant FineCard avec détails
- [ ] Page FinesManagement pour employés
- [ ] Système de notifications toast
- [ ] Badge de notifications sur navigation
- [ ] Modal de paiement d'amende
- [ ] Dashboard avec statistiques

---

## Sprint 5 : Dashboard & Analytics (2 semaines)

### User Stories
- **US-5.1** : En tant qu'employé, je veux voir un tableau de bord avec les statistiques
- **US-5.2** : En tant qu'employé, je veux voir les documents les plus empruntés
- **US-5.3** : En tant qu'employé, je veux générer des rapports mensuels
- **US-5.4** : En tant que client, je veux voir un historique de mes emprunts

### Tasks
#### Back-end
- [ ] Créer AnalyticsController
- [ ] Endpoints pour statistiques générales
- [ ] Query pour documents populaires
- [ ] Service de génération de rapports
- [ ] Endpoint historique emprunts client
- [ ] Optimisation des requêtes avec @Query
- [ ] Cache des statistiques

#### Front-end
- [ ] Dashboard employé avec graphiques
- [ ] Intégrer une librairie de charts (Chart.js/Recharts)
- [ ] Page Analytics avec KPIs
- [ ] Page ClientHistory
- [ ] Composants de statistiques réutilisables
- [ ] Export de données en CSV/PDF
- [ ] Responsive design pour mobile

---

## Sprint 6 : UI/UX Polish & Testing (2 semaines)

### User Stories
- **US-6.1** : En tant qu'utilisateur, je veux une interface responsive et intuitive
- **US-6.2** : En tant qu'utilisateur, je veux des temps de chargement optimisés
- **US-6.3** : En tant qu'administrateur, je veux que l'application soit robuste et testée

### Tasks
#### Back-end
- [ ] Optimisation des performances (lazy loading, caching)
- [ ] Documentation API avec Swagger
- [ ] Tests de charge et performance
- [ ] Logs et monitoring
- [ ] Sécurisation avancée (rate limiting)
- [ ] Configuration pour production

#### Front-end
- [ ] Design system complet avec Tailwind
- [ ] Animations et transitions fluides
- [ ] Tests E2E avec Cypress
- [ ] Tests unitaires composants (Jest/React Testing Library)
- [ ] Optimisation bundle (code splitting)
- [ ] PWA features (offline, notifications)
- [ ] Accessibilité (ARIA, keyboard navigation)

---

## Technologies recommandées

### Back-end additions
- Spring Security + JWT
- Spring Data JPA queries optimisées
- SpringDoc OpenAPI (Swagger)
- Spring Cache
- Spring Scheduler

### Front-end additions
- React Router Dom
- Axios pour les appels API
- React Query/TanStack Query (cache & sync)
- React Hook Form (gestion formulaires)
- Headless UI (composants accessibles)
- Framer Motion (animations)
- Chart.js ou Recharts (graphiques)

### Testing & Quality
- JUnit 5 + Mockito (back-end)
- TestContainers (tests d'intégration)
- Jest + React Testing Library
- Cypress (E2E)
- ESLint + Prettier
- SonarQube (qualité code)

## Estimation globale
- **Durée totale** : 12 semaines
- **Équipe recommandée** : 2-3 développeurs
- **Architecture** : Monolithe avec API REST
- **Déploiement** : Docker + CI/CD