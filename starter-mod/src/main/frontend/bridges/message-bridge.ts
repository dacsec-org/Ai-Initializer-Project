import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./connection-factory";
import { MessageAction } from "../enums/MessageAction";

const SERVICE = "messages-service";

/**
 * <h1>{@link MessageBridge}</h1>
 */
export const MessageBridge
  = (action: MessageAction): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "processMessages",
      { action })
  ).pipe(map(response => response));
};
