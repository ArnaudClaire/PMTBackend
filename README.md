# Project Management Tool (PMT)

Plateforme collaborative de gestion de projet pour équipes de développement.

## Stack technique

Frontend :

- Angular

Backend :

- Spring Boot

Base de données :

- PostgreSQL

CI/CD :

- GitHub Actions

Conteneurisation :

- Docker

---

## Architecture du modèle métier

Le diagramme UML ci-dessous représente les entités principales du système
Project Management Tool (PMT) ainsi que leurs relations.

```mermaid
classDiagram
direction TB

class BaseEntity {
  <<abstract>>
  +Long id
  +Timestamp createdAt
  +Timestamp updatedAt
}

class User {
  +String username
  +String email
  +String passwordHash
  +register(username, email, password)
  +login(email, password)
}

class Project {
  +String name
  +String description
  +LocalDate startDate
  +createProject(name, description, startDate)
}

class ProjectMember {
  +ProjectRole role
  +Timestamp joinedAt
  +assignRole(role)
}

class ProjectInvitation {
  +String email
  +ProjectRole role
  +InvitationStatus status
  +sendInvitation()
  +accept()
  +decline()
}

class Task {
  +String title
  +String description
  +TaskStatus status
  +TaskPriority priority
  +LocalDate dueDate
  +LocalDate endDate
  +updateTask()
  +assignTo(user)
}

class TaskHistory {
  +TaskHistoryAction actionType
  +String fieldName
  +String oldValue
  +String newValue
  +recordChange()
}

class Notification {
  +NotificationType type
  +NotificationStatus status
  +String message
  +Timestamp sentAt
  +send()
}

class ProjectRole {
  <<enumeration>>
  ADMIN
  MEMBER
  OBSERVER
}

class InvitationStatus {
  <<enumeration>>
  PENDING
  ACCEPTED
  DECLINED
}

class TaskStatus {
  <<enumeration>>
  TODO
  IN_PROGRESS
  DONE
}

class TaskPriority {
  <<enumeration>>
  LOW
  MEDIUM
  HIGH
}

class TaskHistoryAction {
  <<enumeration>>
  CREATED
  UPDATED
  ASSIGNED
  STATUS_CHANGED
  COMPLETED
}

class NotificationType {
  <<enumeration>>
  TASK_ASSIGNED
  INVITATION_SENT
}

class NotificationStatus {
  <<enumeration>>
  SENT
  READ
}

BaseEntity <|-- User
BaseEntity <|-- Project
BaseEntity <|-- ProjectMember
BaseEntity <|-- ProjectInvitation
BaseEntity <|-- Task
BaseEntity <|-- TaskHistory
BaseEntity <|-- Notification

Project *-- Task : contient
Task *-- TaskHistory : historique
Project *-- ProjectMember : membres
User *-- Notification : possède
Project o-- ProjectInvitation : invitations

User "1" --> "0..*" Project : crée
User "1" --> "0..*" ProjectMember : participe
User "1" --> "0..*" Task : crée
User "0..1" --> "0..*" Task : assigné
User "1" --> "0..*" TaskHistory : modifie
Task "1" --> "0..*" Notification : déclenche

ProjectMember --> ProjectRole
ProjectInvitation --> InvitationStatus
Task --> TaskStatus
Task --> TaskPriority
TaskHistory --> TaskHistoryAction
Notification --> NotificationType
Notification --> NotificationStatus
```mermaid
