# Curl Commands

## Create Vote
Example of voting curl command
```bash
curl -X POST "http://localhost:8080/votes/submit" -H  "accept: application/json" -H  "Content-Type: application/json" -d '{"lobbyName":"asdf","user":"fdsa","selection":"jkl;","count":0}'
```

## Create Lobby
Example of lobby creation
```bash
curl -X POST -d '{"lobbyName":"foo","options":["t99", "jstris","ppt","nullpomino","TDS","MTG","TGM","TGM2","TGM3"]}' -H "Content-Type: application/json" localhost:8080/lobby/create
```

