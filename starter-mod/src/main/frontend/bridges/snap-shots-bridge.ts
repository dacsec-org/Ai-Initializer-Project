import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./connection-factory";
import { SnapshotsActions } from '../enums/snapshots-actions';

const SERVICE = "snapshots-service"; // Name from @Bridge annotation

/**
 * <h1>{@link SnapshotsBridge}</h1>
 */
export const SnapshotsBridge = (action: SnapshotsActions): Observable<any> => {
  return from(
    client.call(SERVICE, "processSnapshots", { action })
  ).pipe(map(response => response));
}
