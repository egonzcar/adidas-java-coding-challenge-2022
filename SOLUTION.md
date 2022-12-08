# What I do?

## Adi-Club Service

I create an In-memory database for this approach. This fake database will generate 10 different emails of members
registered, with '@gmail' or '@adiclub' depending on if the generation counter is even or not.
This will be used by the 'Priority-sale Service' to retrieve the emails of the members, and then call the service again
to retrieve the information of each member.
I decided to use this method instead of enter the emails from the input of the endpoint because I think is more
'realistic' retrieve the data from the database instead of take it from the input.

## Priority-sale Service

This is the main microservice who will orchestrate everything from the petition that will enter by the public-service.
When the request from the public-service arrives will begin with recovering all the email list from the adiclub-service,
once it has all the different emails will make the different request for every email to recover all the information of
each member.
Once we have all the information list will begin with the sort of the list with that following steps:

- Make the difference between the Aciclub members and the normal users in 2 different list
- Sort the list 2 list first by registration date and then by points, with this order to have already the points draws
  already sort by date.
- Concat the two list with the Adiclub members first for the preference

Once we have the sorted list of members we send the list to the producer who will send our Email event to the Kafka
queue (added container in docker compose).

## Email-Service

This service is really simple, just have a consumer that will take the event messages from Kafka queue and send the
emails (log trace) to the members by the order of the queue.

## Public Service

This service is even simpler than the email-service because will be a bypass from the external request to the
members-service API. In this service we can set up all the security to make the funnel to our system and avoid the
malicious request.

## CI/CD proposal

I sketched the proposal using PlantUML, you will find the file by the name 'ci-cd-proposal.puml' in the root of the
project.

## Kubernetes manifests

I added in the folder 'kubernetes_manifests' all the needed manifests to deploy the cluster in kubernetes.
Also added in the docker-compose the property 'labels: kompose.service.type: LoadBalancer' to the public-service to make
it visible from the exterior of the cluster.