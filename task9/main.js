import express from "express";
const app = express();
const port = 3000;

app.use(express.json());

Number.prototype.isPrime = function () {
  if (this <= 1) return false;
  if (this <= 3) return true;
  for (let i = 2; i <= this - 1; i++) {
    if (this % i === 0) return false;
  }
  return true;
};

app.post("/isPrime", (req, res) => {
  let num = req.body.num;

  if (typeof num !== "number") {
    return res.status(400).json({ error: "Input must be a number" });
  }

  new Promise((resolve, reject) => {
    if (num.isPrime()) {
      resolve(1);
    } else {
      reject(0);
    }
  })
    .then((result) => res.json({ "is prime: ": result }))
    .catch((error) => res.json({ "is prime: ": error }));
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
