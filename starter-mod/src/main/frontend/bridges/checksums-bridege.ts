import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./ConnectionFactory";
import { ChecksumActions } from '../enums/ChecksumActions';

const SERVICE = "checksums-service";

/**
 * <h1>{@link ChecksumsBridge}</h1>
 */
export const ChecksumsBridge = (action: ChecksumActions, filePath: string): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "calculateChecksum",
      { action, filePath }
    )
  ).pipe(map(response => response));
};
