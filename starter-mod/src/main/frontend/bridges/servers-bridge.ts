import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./connection-factory";
import { ServerTypes } from "../enums/ServerTypes";
import { ServerActions } from "../enums/ServerActions";

const SERVICE = "servers-service";

/**
 * <h1>{@link ServersBridge}</h1>
 */
export const ServersBridge = (type: ServerTypes, action: ServerActions): Observable<any> => {
  return from(
    client.call(SERVICE, "processServer", { type, action })
  ).pipe(map(response => response));
};
