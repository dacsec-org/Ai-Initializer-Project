import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./connection-factory";
import { ServerTypes } from "../enums/server-types";
import { ServerActions } from "../enums/server-actions";

const SERVICE = "servers-service";

/**
 * <h1>{@link ServersBridge}</h1>
 */
export const ServersBridge = (type: ServerTypes, action: ServerActions): Observable<any> => {
  return from(
    client.call(SERVICE, "processServer", { type, action })
  ).pipe(map(response => response));
};
