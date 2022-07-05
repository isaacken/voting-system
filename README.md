# Local environment setup

```bash
git clone https://github.com/isaacken/voting-system.git
cd voting-system
docker compose up -d
```

# Routes

## Create agenda
**POST** `/agenda`

```json
    {
        "question": "Should rice be on top of beans?"
    }
```

## Start vote session
**POST** `/voting-session/start`

```json    
    {
        "agenda_id": "62bd2ae7d435a23d8127f3c1",
        "session_duration": 1
    }
```

## Vote an agenda
**POST** `/vote`

Possible values for `vote_value` are *YES* and *NO*

```json
    {
        "voter_id": "59238833095",
        "agenda_id": "62bd2ae7d435a23d8127f3c1",
        "vote_value": "YES"
    }
```
