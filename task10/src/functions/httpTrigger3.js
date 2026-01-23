const { app } = require("@azure/functions");

app.http("httpTrigger3", {
  methods: ["GET", "POST"],
  authLevel: "anonymous",
  handler: async (request, context) => {
    const { base, exponent } = await request.json();

    return { status: 200, jsonBody: { result: Math.pow(base, exponent) } };
  },
});
