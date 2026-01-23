const { app } = require("@azure/functions");

function isEvenNumber(num) {
  return num % 2 === 0;
}

app.http("httpTrigger2", {
  methods: ["POST"],
  authLevel: "anonymous",
  handler: async (request, context) => {
    const { number1 } = await request.json();

    return {
      status: 200,
      jsonBody: { isEven: isEvenNumber(number1) },
    };
  },
});
