# corp workflows Flowable Engage

## Setting up the needed Infrastructure
Please check the following links on how to setup the infrastructure for Flowable manually without
using Docker:

- [Postgres Database](https://documentation.flowable.com/latest/admin/installs/engage-full/#database-1)
- [Elasticsearch](https://documentation.flowable.com/latest/admin/installs/engage-full/#elasticsearch-1)
- [ActiveMQ](https://documentation.flowable.com/latest/admin/installs/engage-full/#activemq-engage)
- [Flowable Design](https://documentation.flowable.com/latest/admin/installs/engage-full/#flowable-design)
- [Flowable Control](https://documentation.flowable.com/latest/admin/installs/engage-full/#flowable-control)

Do not forget to set the deployment and cluster address properties of Design and Control to point to
`http://localhost:8090`.  
For an easier setup with help of Docker check chapter [Setting up the needed Infrastructure with Docker](#Setting up the needed Infrastructure with Docker).

## Starting the project
Afterwards you can start the Spring Boot application defined in `com.corp.workflows.CorpWorkflowsApplication`. In order to achieve this,
you can build the executable war file with maven and execute `java -jar` pointing to the built war or create a new IDE Run Configuration. 
Then open `http://localhost:8090` in the browser and use one of the users specified below in this document.
You may want to login twice with different users on different browsers to test out the chatting functionality.

The built war file can also be deployed to a supported servlet container to be started with it.
You would then need to adapt the Flowable Design and Control properties to fit the chosen web context accordingly.

## Helpful links
After both docker-compose and the application are started, these are the links for the different applications:

- http://localhost:8090 - Flowable Engage
- http://localhost:8091 - Flowable Design
- http://localhost:8092 - Flowable Control
- http://localhost:8161/admin/ - Active MQ management (user: admin, pass: admin)

## Setting up the needed Infrastructure with Docker
Log in to docker `docker login repo.flowable.com`, use your Flowable login.
You will also need an appropriate Flowable license stored in `~/.flowable/flowable.license`.
Also a decent amount of memory is needed for your docker VM. At least 4 GB are suggested.
Navigate to `/docker`, then execute `docker-compose up`. Services such as Elasticsearch and Database will be started.

- Flowable Design is already preconfigured to deploy the models into the engine.
- Flowable Control is already preconfigured to connect to the engine.

### Containers
If you need to recreate the containers, perform the following actions:
- Make sure the application and the docker containers are stopped.
- Execute `docker-compose down` inside the `/docker` directory. This will remove the created containers.

### Data
Data created by the database and Elasticsearch are stored in docker volumes `data_db` and `data_es.
This allows you to purge and recreate the containers without loosing any data.

If you need a clean state for the database and elasticsearch just execute `docker-compose down -v`inside the `/docker` directory.
The volumes will be recreated as soon as you restart the containers.  
BE CAREFUL AS WITH THIS YOU WILL LOOSE ALL YOUR DATA STORED IN THE DATABASE AND ELASTICSEARCH!

## Sample users
| User | User Definition Key | Login | Password |
| -------------| ------------- | ------------- | ------------- |
| Flowable Administrator | admin-flowable | admin | test |
| workflows Administrator | admin-workflows | workflows.admin | test |
| workflows Work Administrator | admin-work | work.admin | test |
| workflows Work User | user-work | work.user | test |
| workflows Engage Administrator | admin-engage | engage.admin | test |
| workflows Engage User | user-engage | engage.user | test |

## Change Log

### 0.0.1
- Initial commit