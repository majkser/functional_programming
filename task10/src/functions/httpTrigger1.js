const { app } = require("@azure/functions");

function addNumbers(num1, num2) {
  return num1 + num2;
}

app.http("httpTrigger1", {
  methods: ["POST"],
  authLevel: "anonymous",
  handler: async (request, context) => {
    const { number1, number2 } = await request.json();

    return {
      status: 200,
      jsonBody: { sum: addNumbers(number1, number2) },
    };
  },
});
