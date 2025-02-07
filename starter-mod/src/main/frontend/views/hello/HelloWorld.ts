import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import client_1 from "../connect-client.default";

async function sayHello_1(name: string, init?: EndpointRequestInit_1): Promise<string> {
  return client_1.call("org.dacss.projectinitai.services.HelloWorldService", "sayHello", { name }, init); }
export { sayHello_1 as sayHello };
