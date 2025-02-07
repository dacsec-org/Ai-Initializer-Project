import client_1 from "../connect-client.default";
async function sayHello_1(name, init) {
    return client_1.call("org.dacss.projectinitai.services.HelloWorldService", "sayHello", { name }, init);
}
export { sayHello_1 as sayHello };
//# sourceMappingURL=HelloWorld.js.map