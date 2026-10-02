# Store Demo — dépôt de démonstration GitHub Advanced Security

Ce dépôt existe **uniquement** pour illustrer les fonctionnalités de GitHub Advanced Security
(GitHub Code Security et GitHub Secret Protection) dans un guide de mise en œuvre.

> ⚠️ **Le code de ce dépôt contient des vulnérabilités volontaires.**
> Il ne doit jamais être déployé, réutilisé ou servir de base à une application réelle.

## Ce que l'on y trouve

| Fichier | Faiblesse volontaire |
| --- | --- |
| `DatabaseService.java` | Injection SQL, identifiants en dur |
| `InvoiceService.java` | Traversée de répertoire, injection de commande |
| `CatalogueServlet.java` | Cross-site scripting (XSS) réfléchi |
| `TokenService.java` | Aléa non cryptographique, empreinte MD5 sans sel |
| `pom.xml` | Dépendances obsolètes avec CVE connues |

## Fonctionnalités de sécurité activées

- **Code scanning** avec CodeQL (configuration par défaut)
- **Dependabot** : alertes et mises à jour de sécurité
- **Secret scanning** et **push protection**

Les résultats sont visibles dans l'onglet **Security** du dépôt.
