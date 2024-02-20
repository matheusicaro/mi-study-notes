# Dev Environment

Creates a development environment using Docker with infrastructure dependencies that match our staging and production environments.

## Usage

> **NOTE**: On your first run, Docker will download any images specified in the compose file. Make sure to `npm run pull` or `docker-compose pull` occasionally to update local images.

- Run all dependencies: `npm start` or `docker-compose up`
- Run a single dependency:
  - mongodb: `npm start mongodb`
  - postgres: `npm start postgres`
  - redis: `npm start redis`

When running:

- Display container logs: `npm run logs:mongodb` | `npm run logs:redis` | `npm run logs:postgres`
- Connect to mongo shell: `npm run mongo`
- Connect to psql shell: `npm run psql`
- Connect to redis shell: `npm run redis`

### Local Topics and Queues

The list of topics and queues is automatically kept up to date with the integration environment. When starting Localstack it will automatically create all the topics, queues and subscriptions.

When running `npm start` the container will take a long time to create all the local queues. However, outside the container you can run `npm run init:queues` which initializes all the queues much faster. The container will continue to initialize the queues but this won't cause any conflicts and you don't need to wait for it to finish.

### Filtering

A filter can be provided to the `init:queues` script which will allow you to directly initialize a subset of queues and topics. For example:

```
npm run init:queues -- neo-mart rewards-service
```

This will cause any queues or topics which contain **any** of the provided terms to be created, while others are ignored.
Additionally, subscriptions which **do not** contain one of the provided terms will be ignored to avoid creating subscriptions
to topics which are not included in the filter.
