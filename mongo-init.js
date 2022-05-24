db.createUser(
  {
    user: 'api_user',
    pwd: 'api_user',
    roles: [{ role: 'readWrite', db: 'cosmic' }, { role: 'readWrite', db: 'admin' }],
  },
);
db.createCollection('schedules');

