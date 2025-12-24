FROM haskell:latest

RUN cabal update && \
    cabal install --lib scotty aeson text mtl

COPY main.hs .

RUN ghc --make main.hs -o app

EXPOSE 3000

CMD ["./app"]