import { from, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import client  from './ConnectionFactory';

export class ContentGallery {
  static getGallery(): Observable<any> {
    return from(
      client.call('ContentGalleryService', 'getGallery', {})
    ).pipe(map(response => response));
  }
}
